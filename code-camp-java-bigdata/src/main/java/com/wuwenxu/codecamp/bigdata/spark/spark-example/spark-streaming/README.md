
### 启动

* 先启动服务端  com.data.spark.wordcount.DataServer

监听9082端口，等待客户端发起请求


* 然后启动客户端；获取TCP套接字，并运算，将结果打印

```
./bin/spark-submit --class com.data.spark.wordcount.StreamingWordCountTaskClient  --master spark://192.168.0.14:7077 spark-streaming-1.0-SNAPSHOT.jar
```





