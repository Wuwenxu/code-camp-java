package com.wuwenxu.codecamp.base.aliyun.QLExpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.DynamicParamsUtil;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.junit.Test;

/**
 * @ClassName : QLExpressTest
 * @Author : liuhaolai
 * @Date : 2019年12月03日 9:00 上午
 * @Version : 1.0.0
 */
public class QLExpressTest {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();

        //todo: 简单模板
        //禁止显示与可序列化类缺少serialVersionUID字段相关的警告
        DefaultContext<String, Object> context1 = new DefaultContext<String, Object>();
        context1.put("a",1);
        context1.put("b",2);
        context1.put("c",3);
        String express = "a+b*c";
        Object r1 = runner.execute(express, context1, null, true, false);
        System.out.println("a+b*c====>>>"+r1);

        //todo: 扩展操作符：Operator
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("则", "then",null);
        runner.addOperatorWithAlias("否则", "else",null);

        String exp = "如果  (100+100+100>270) 则 {return 1;} 否则 {return 0;}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object r2 =  runner.execute(exp,context,null,false,false,null);
        System.out.println("如果  (100+100+0>270) 则 {return 1;} 否则 {return 0;}====>>>"+r2);


//        //TODO： 关键字：addOperator  地址运算符
//        ExpressRunner runner = new ExpressRunner();
//        DefaultContext<String, Object> addOperatorContext = new DefaultContext<String, Object>();
//        runner.addOperator("join",new JoinOperator());
//        Object r3 = runner.execute("1 join 2 join 3", addOperatorContext, null, false, false);
//        System.out.println("1 join 2 join 3====>>>"+r3);


//        //todo：关键字：replaceOperator  替换运算符
//        ExpressRunner runner = new ExpressRunner();
//        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        runner.replaceOperator("+",new JoinOperator());
//        Object r = runner.execute("1 + 2 + 3", context, null, false, false);
//        System.out.println("1 + 2 + 3=====>>>"+r);
////返回结果  [1, 2, 3]
//
//        //todo：关键字：addFunction
//        ExpressRunner runner = new ExpressRunner();
//        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        runner.addFunction("join",new JoinOperator());
//        Object r = runner.execute("join(1,2,3)", context, null, false, false);
//        System.out.println("join(1,2,3)=====>>>"+r);
////返回结果  [1, 2, 3]

//
//        //todo：绑定java类或者对象的method
//        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs",
//                new String[] { "double" }, null);
//        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(),
//                "upper", new String[] { "String" }, null);
//
//        runner.addFunctionOfServiceMethod("打印", System.out, "println",new String[] { "String" }, null);
//        runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains",
//                new Class[] { String.class, String.class }, null);
//
//        String expMethod = "取绝对值(-100);转换为大写(\"hello world\");打印(\"你好吗？\");contains(\"helloworld\",\"aeiou\")";
//        Object rMethod =  runner.execute(expMethod, context, null, false, false);
//        System.out.println("Method=====>>>"+rMethod);
//
//
//
        //todo: 关键字 macro宏定义
        runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
        runner.addMacro("是否优秀", "计算平均成绩>90");
        IExpressContext<String, Object> contextMacro =new DefaultContext<String, Object>();
        contextMacro.put("语文", 88);
        contextMacro.put("数学", 99);
        contextMacro.put("英语", 95);
        Object resultMacro = runner.execute("计算平均成绩", contextMacro, null, false, false);
        System.out.println("resultMacro======>>>>"+resultMacro);
//
//
//        //todo :编译脚本，查询外部需要定义的变量和函数。
//        String expressFn = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
//        ExpressRunner runnerFn = new ExpressRunner(true,true);
//        String[] names = runnerFn.getOutVarNames(expressFn);
//        for(String s:names){
//            System.out.println("var : " + s);
//        }
    }

    @Test
    public void testMethodReplace() throws Exception {
        //todo: 关于不定参数的使用
        ExpressRunner runner = new ExpressRunner();
        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        runner.addFunctionOfServiceMethod("getTemplate", this, "getTemplate", new Class[]{Object[].class}, null);

        //(1)默认的不定参数可以使用数组来代替
        Object r = runner.execute("getTemplate([11,'22',33L,true])", expressContext, null,false, false);
        System.out.println(r);
        //(2)像java一样,支持函数动态参数调用,需要打开以下全局开关,否则以下调用会失败
        DynamicParamsUtil.supportDynamicParams = true;
        r = runner.execute("getTemplate(11,'22',33L,true)", expressContext, null,false, false);
        System.out.println(r);
    }
    //等价于getTemplate(Object[] params)
    public Object getTemplate(Object... params) throws Exception{
        String result = "";
        for(Object obj:params){
            result = result+obj+",";
        }
        return result;
    }

    @Test
    public void testSet() throws Exception {
        //todo:关于集合的快捷写法
        ExpressRunner runner = new ExpressRunner(false,false);
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        String express = "abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2);";
        Object r = runner.execute(express, context, null, false, false);
        System.out.println("abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2)====>>"+r);
        express = "abc = NewList(1,2,3); return abc.get(1)+abc.get(2)";
        r = runner.execute(express, context, null, false, false);
        System.out.println("abc = NewList(1,2,3); return abc.get(1)+abc.get(2)===>>"+r);
        express = "abc = [1,2,3]; return abc[1]+abc[2];";
        r = runner.execute(express, context, null, false, false);
        System.out.println("abc = [1,2,3]; return abc[1]+abc[2];====>>>"+r);
    }
}
