package com.wuwenxu.codecamp.base.aliyun.call;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author zhaox 
 * date 2020/4/24 11:26
 */
@SuppressWarnings("all")
public class CallSmartDemo {

    private static final Logger LOG = LoggerFactory.getLogger(CallSmartDemo.class);

    public static void main(String[] args) throws ClientException {
    	long start = System.currentTimeMillis();
        CallSmartUtil requester = createRequester();
        JSONObject strategy = createStrategy();
        long end = System.currentTimeMillis();
        //创建作业组
//        String jobGroupId = requester.createJobGroup(strategy, "测试作业组"+UUID.randomUUID().toString());
        String jobGroupId ="13bbe012-77af-44af-adf5-19947fa93e4a";
        //此处添加作业
        List<JSONObject> jobs = new ArrayList<>();
//        jobs.add(createJob("13203706127"));
//        jobs.add(createJob("18538194241"));
//        jobs.add(createJob("13688436636"));
//        jobs.add(createJob("18600574818"));
      //  jobs.add(createJob("15837156942"));
//      jobs.add(createJob("18617092039"));
        //jobs.add(createJob("18538194241"));
        jobs.add(createJob("18832173889"));
        //触发外呼
//        requester.assignJobs(jobGroupId, jobs);
        
      //jobgroupid-----------------jobid
      //	46b857a5-bae8-409b-a299-25dfbe93d22d      ---------   1384e1dc-3ddc-47be-a88d-05af7f901c99
      //    7ac65121-4b20-41d5-af6e-906e03341d59      ---------   7fb36341-7165-4625-9bdf-0e899e677cc8
        
//        JSONObject result = requester.listJobsByGroup(jobGroupId,1,10);
//        JSONArray array = result.getJSONArray("List");
//        for (int i = 0; i < array.size(); i++) {
//			JSONObject a = array.getJSONObject(i);
//			System.out.println(a.getString("Id"));
//		}
        
        
//        JSONObject result = requester.getJob("c4828faf-242e-4c92-83e1-cbd7c314486b");      //2020.05.07  16:38
//        JSONArray array = result.getJSONArray("Tasks");
//        for (int i = 0; i < array.size(); i++) {
//			JSONObject a = array.getJSONObject(i);
//			System.out.println(a);
//		}
//     
//        requester.suspendJobs(jobGroupId);
//        requester.cancelJobs(jobGroupId);
//        requester.resumeJobs(jobGroupId);


        JSONObject result = requester.getJob("3bb8e9bc-2f09-4670-a2e0-3d7ec19beec7");      //2020.05.07  16:38
//        JSONObject result = requester.getJob("7fb36341-7165-4625-9bdf-0e899e677cc8");      //2020.05.07  16:38
        JSONArray array = result.getJSONArray("Tasks");
        for (int i = 0; i < array.size(); i++) {
			JSONObject a = array.getJSONObject(i);
			System.out.println(a);
		}
        
        List <String> list = new ArrayList<>();
 //       list.add("cb38f598-e366-405b-afa0-e42e3ffaf4d9");
//        list.add("0c9695fb-870a-46c8-8147-e590a8c67387");
//    	list.add("72c35d24-3320-4383-a06f-49cdc50aedc4");
//    	list.add("99050e13-798e-4c89-9424-4687b81c06d0");
//    	JSONArray result = requester.getJobs(list);
 //   	JSONObject result2 = requester.getJob("bb681402-78a5-4fc6-981e-8fe8c09e6270");
        

        
//        String sessionId="f1d1d3cc-8359-417a-857b-ecf4d9e3f21c";
////        String sessionId = "5a40a6b5-30f1-43ba-aea1-0b4d352270eb";  //高建宇的回答  对应jobid  f1d1d3cc-8359-417a-857b-ecf4d9e3f21c
//        JSONArray conversationTags = requester.getConversationTags(sessionId, "2374");
//        System.out.println(conversationTags);
//        JSONObject result = requester.queryJobs(jobGroupId);
        
    }

    private static JSONObject createJob(String phoneNumber) {

        JSONObject job = new JSONObject();
        List<JSONObject> extras = new ArrayList<>();
        extras.add(new JSONObject().fluentPut("key", "ServiceId").fluentPut("value", "8711"));
        extras.add(new JSONObject().fluentPut("key", "TenantId").fluentPut("value", "7561"));
        extras.add(new JSONObject().fluentPut("key", "several").fluentPut("value", "6"));
		extras.add(new JSONObject().fluentPut("key", "local").fluentPut("value", "召唤师峡谷"));

        job.put("extras", extras);

        JSONObject contact = new JSONObject();
        contact.put("phoneNumber", phoneNumber);
        contact.put("name", "小伟伟");
        contact.put("referenceId", UUID.randomUUID().toString());
       job.put("contacts", Collections.singletonList(contact));
    //    List<JSONObject> contacts = new ArrayList<>();
    //    contacts.add(new JSONObject().fluentPut("phoneNumber", "18538194241").fluentPut("name", "赵浪浪").fluentPut("referenceId", UUID.randomUUID().toString()));
    //    contacts.add(new JSONObject().fluentPut("phoneNumber", "0018617092039").fluentPut("honorific", "曾先生").fluentPut("name", "曾志铭").fluentPut("referenceId", UUID.randomUUID().toString()));
    //    job.put("contacts", contacts);

        return job;
    }


    private static JSONObject createStrategy() {
        JSONObject strategy = new JSONObject();
        //失败后最大重试数
        strategy.put("maxAttemptsPerDay", 3);
        //重复拨打间隔时间 分钟
        strategy.put("minAttemptInterval", 10);
        //设置策略名字
      //  strategy.put("name", UUID.randomUUID().toString());
        strategy.put("name", "测试作业组测试策略");
        return strategy;
    }

    static CallSmartUtil createRequester() throws ClientException {
        DefaultProfile.addEndpoint(
        		"cn-shanghai","Chatbot", "47.98.152.59");
        IClientProfile profile = DefaultProfile.getProfile(
                "cn-shanghai", "7FFAECAD2EC13DF6", "C0456A5GABF33D2H12DHA41FF01021E6");

        return new CallSmartUtil.RequesterBuilder()
        		//示例id，机器人id
                .instanceId("f277e5bf-5814-4424-bb96-931e05bf01f1")
                //场景id
                .scenarioId("49ab27be-63c8-4fe4-9436-b39052762f45")
                //话术id
                .scriptId("b1b3b37f-5e6f-4e5d-86a7-33bf5ef99050")
                .callingNumbers(new ArrayList<String>(){{
                    //增加自己的主叫号码列表
                	add("ALI_OUTBOUND_1101");
                }})
                .client(new DefaultAcsClient(profile))
                .build();
    }



}
