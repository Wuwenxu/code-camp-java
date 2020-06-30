package com.wuwenxu.codecamp.base.point;


import java.util.Arrays;
import java.util.List;

/**
 * 用于点与多边形位置关系的判断
 *
 * @author zhengtian
 * @date 2013-8-5 上午11:59:35
 */
public class GraphUtils {

    private static final Double PI = Math.PI;

    private static final Double PK = 180 / PI;

    /**
     * 判断点是否在多边形内(基本思路是用交点法)
     *
     * @param point
     * @param boundaryPoints
     * @return
     */
    public static boolean isPointInPolygon(BmapPoint point, BmapPoint[] boundaryPoints) {
        // 防止第一个点与最后一个点相同
        if (boundaryPoints != null && boundaryPoints.length > 0 &&
                boundaryPoints[boundaryPoints.length - 1].equals(boundaryPoints[0])) {
            boundaryPoints = Arrays.copyOf(boundaryPoints, boundaryPoints.length - 1);
        }
        int pointCount = boundaryPoints.length;

        // 首先判断点是否在多边形的外包矩形内，如果在，则进一步判断，否则返回false
        if (!isPointInRectangle(point, boundaryPoints)) {
            return false;
        }

        // 如果点与多边形的其中一个顶点重合，那么直接返回true
        for (int i = 0; i < pointCount; i++) {
            if (point.equals(boundaryPoints[i])) {
                return true;
            }
        }

        /**
         * 基本思想是利用X轴射线法，计算射线与多边形各边的交点，如果是偶数，则点在多边形外，否则在多边形内。还会考虑一些特殊情况，如点在多边形顶点上
         * ， 点在多边形边上等特殊情况。
         */

        // X轴射线与多边形的交点数
        int intersectPointCount = 0;
        // X轴射线与多边形的交点权值
        float intersectPointWeights = 0;
        // 浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        // 边P1P2的两个端点
        BmapPoint point1 = boundaryPoints[0], point2;
        // 循环判断所有的边
        for (int i = 1; i <= pointCount; i++) {
            point2 = boundaryPoints[i % pointCount];

            /**
             * 如果点的y坐标在边P1P2的y坐标开区间范围之外，那么不相交。
             */

            if (point.getLat() < Math.min(point1.getLat(), point2.getLat()) ||
                    point.getLat() > Math.max(point1.getLat(), point2.getLat())) {
                point1 = point2;
                continue;
            }

            /**
             * 此处判断射线与边相交
             */

            if (point.getLat() > Math.min(point1.getLat(), point2.getLat()) &&
                    point.getLat() < Math.max(point1.getLat(), point2.getLat())) { // 如果点的y坐标在边P1P2的y坐标开区间内
                if (point1.getLng() == point2.getLng()) { // 若边P1P2是垂直的

                    if (point.getLng() == point1.getLng()) {                           // 若点在垂直的边P1P2上，则点在多边形内

                        return true;
                    } else if (point.getLng() < point1.getLng()) {                           // 若点在在垂直的边P1P2左边，则点与该边必然有交点

                        ++intersectPointCount;
                    }
                } else { // 若边P1P2是斜线

                    if (point.getLng() <= Math.min(point1.getLng(), point2.getLng())) { // 点point的x坐标在点P1和P2的左侧

                        ++intersectPointCount;
                    } else if (point.getLng() > Math.min(point1.getLng(), point2.getLng()) && point.getLng() < Math.max(point1.getLng(), point2.getLng())) { // 点point的x坐标在点P1和P2的x坐标中间

                        double slopeDiff = 0.00000000d;
                        if (point1.getLat() > point2.getLat()) {
                            slopeDiff = (point.getLat() - point2.getLat()) / (point.getLng() - point2.getLng()) - (point1.getLat() - point2.getLat()) / (point1.getLng() - point2.getLng());
                        } else {
                            slopeDiff = (point.getLat() - point1.getLat()) / (point.getLng() - point1.getLng()) - (point2.getLat() - point1.getLat()) / (point2.getLng() - point1.getLng());
                        }
                        if (slopeDiff > 0) {
                            if (slopeDiff < precision) { // 由于double精度在计算时会有损失，故匹配一定的容差。经试验，坐标经度可以达到0.0001
                                // 点在斜线P1P2上

                                return true;
                            } else {                                   // 点与斜线P1P2有交点

                                intersectPointCount++;
                            }
                        }
                    }
                }
            } else {                   // 边P1P2水平

                if (point1.getLat() == point2.getLat()) {
                    if (point.getLng() <= Math.max(point1.getLng(), point2.getLng()) && point.getLng() >= Math.min(point1.getLng(), point2.getLng())) {                           // 若点在水平的边P1P2上，则点在多边形内

                        return true;
                    }
                }
                /**
                 * 判断点通过多边形顶点
                 */

                if (((point.getLat() == point1.getLat() && point.getLng() < point1.getLng())) || (point.getLat() == point2.getLat() && point.getLng() < point2.getLng())) {
                    if (point2.getLat() < point1.getLat()) {
                        intersectPointWeights += -0.5;
                    } else if (point2.getLat() > point1.getLat()) {
                        intersectPointWeights += 0.5;
                    }
                }
            }
            point1 = point2;
        }
        if ((intersectPointCount + Math.abs(intersectPointWeights)) % 2 == 0) { // 偶数在多边形外

            return false;
        } else {  // 奇数在多边形内

            return true;
        }
    }

    /**
     * 判断点是否在矩形内在矩形边界上，也算在矩形内(根据这些点，构造一个外包矩形)
     *
     * @param point          点对象
     * @param boundaryPoints 矩形边界点
     * @return
     */

    public static boolean isPointInRectangle(BmapPoint point, BmapPoint[] boundaryPoints) {
        BmapPoint southWestPoint = getSouthWestPoint(boundaryPoints);  // 西南角点

        BmapPoint northEastPoint = getNorthEastPoint(boundaryPoints);  // 东北角点

        return (point.getLng() >= southWestPoint.getLng() && point.getLng() <= northEastPoint.getLng() && point.getLat() >= southWestPoint.getLat() && point.getLat() <= northEastPoint.getLat());
    }

    /**
     * 根据这组坐标，画一个矩形，然后得到这个矩形西南角的顶点坐标
     *
     * @param vertexs
     * @return
     */

    private static BmapPoint getSouthWestPoint(BmapPoint[] vertexs) {
        double minLng = vertexs[0].getLng(), minLat = vertexs[0].getLat();
        for (BmapPoint bmapPoint : vertexs) {
            double lng = bmapPoint.getLng();
            double lat = bmapPoint.getLat();
            if (lng < minLng) {
                minLng = lng;
            }
            if (lat < minLat) {
                minLat = lat;
            }
        }
        return new BmapPoint(minLng, minLat);
    }

    /**
     * 根据这组坐标，画一个矩形，然后得到这个矩形东北角的顶点坐标
     *
     * @param vertexs
     * @return
     */

    private static BmapPoint getNorthEastPoint(BmapPoint[] vertexs) {
        double maxLng = 0.00000000d, maxLat = 0.00000000d;
        for (BmapPoint bmapPoint : vertexs) {
            double lng = bmapPoint.getLng();
            double lat = bmapPoint.getLat();
            if (lng > maxLng) {
                maxLng = lng;
            }
            if (lat > maxLat) {
                maxLat = lat;
            }
        }
        return new BmapPoint(maxLng, maxLat);
    }

    /**
     * 根据输入的地点坐标计算中心点
     *
     * @param jwdzList
     * @return
     */
    public static BmapPoint getCenterPoint(List<BmapPoint> jwdzList) {
        int total = jwdzList.size();
        double X = 0, Y = 0, Z = 0;
        for (BmapPoint g : jwdzList) {
            double lat, lon, x, y, z;
            lat = g.getLat() * Math.PI / 180;
            lon = g.getLng() * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        return new BmapPoint(Lon * 180 / Math.PI, Lat * 180 / Math.PI);
    }

    /**
     * @param lat_a a的经度
     * @param lng_a a的维度
     * @param lat_b b的经度
     * @param lng_b b的维度
     * @return 距离
     * @Description: 根据经纬度计算两点之间的距离
     * @author
     */
    public static String getDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
        double t1 =
                Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);
        double t2 =
                Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);

        double tt = Math.acos(t1 + t2 + t3);
        String res = (6366000 * tt) + "";
        return res.substring(0, res.indexOf("."));
    }


    public static boolean isPtInPoly(BmapPoint bmapPoint, BmapPoint[] ps) {
        double ALon = bmapPoint.getLng();
        double ALat = bmapPoint.getLat();
        int iSum, iCount, iIndex;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
        if (ps.length < 3) {
            return false;
        }
        iSum = 0;
        iCount = ps.length;
        for (iIndex = 0; iIndex < iCount; iIndex++) {
            if (iIndex == iCount - 1) {
                dLon1 = ps[iIndex].getLng();
                dLat1 = ps[iIndex].getLng();
                dLon2 = ps[0].getLng();
                dLat2 = ps[0].getLng();
            } else {
                dLon1 = ps[iIndex].getLng();
                dLat1 = ps[iIndex].getLng();
                dLon2 = ps[iIndex + 1].getLng();
                dLat2 = ps[iIndex + 1].getLng();
            }
            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    //得到 A点向左射线与边的交点的x坐标：
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);
                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
                    if (dLon < ALon) {
                        iSum++;
                    }
                }
            }
        }
        if ((iSum % 2) != 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

//        System.out.println(getDistanceMax(geoCoordinateList));
        BmapPoint a1 = new BmapPoint(110, 30);
        BmapPoint a2 = new BmapPoint(120, 40);
        BmapPoint a3 = new BmapPoint(130, 30);
        BmapPoint a4 = new BmapPoint(112, 30.00000000001);
        BmapPoint[] bmapPoints = new BmapPoint[3];
        bmapPoints[0] = a1;
        bmapPoints[1] = a2;
        bmapPoints[2] = a3;
        boolean pointInPolygon = isPointInPolygon(a4, bmapPoints);
        System.out.println(pointInPolygon);
    }
}