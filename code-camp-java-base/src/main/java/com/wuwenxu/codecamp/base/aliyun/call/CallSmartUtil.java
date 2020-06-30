package com.wuwenxu.codecamp.base.aliyun.call;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhaox 
 * date 2020/4/24 11:26
 */
@SuppressWarnings("all")
@Slf4j
public class CallSmartUtil {

    private String instanceId;

    private String scenarioId;

    private String scriptId;

    private List<String> callingNumbers;

    private DefaultAcsClient acsClient;

    public static class RequesterBuilder {

        private CallSmartUtil requester = new CallSmartUtil();

        public RequesterBuilder instanceId(String instanceId) {
            requester.instanceId = instanceId;
            return this;
        }

        public RequesterBuilder scriptId(String scriptId) {
            requester.scriptId = scriptId;
            return this;
        }

        public RequesterBuilder scenarioId(String scenarioId) {
            requester.scenarioId = scenarioId;
            return this;
        }

        public RequesterBuilder callingNumbers(List<String> callingNumbers) {
            requester.callingNumbers = callingNumbers;
            return this;
        }

        public RequesterBuilder client(DefaultAcsClient acsClient) {
            requester.acsClient = acsClient;
            return this;
        }

        public CallSmartUtil build() {
            return requester;
        }

    }

    public String createJobGroup(JSONObject strategy, String groupName){

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("CreateJobGroup");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);

        commonRequest.putQueryParameter("instanceId", instanceId);
        //文档是JobGroupName？？？
        commonRequest.putQueryParameter("name", groupName);
        commonRequest.putQueryParameter("scenarioId", scenarioId);
        commonRequest.putQueryParameter("scriptId", scriptId);
        commonRequest.putQueryParameter("strategyJson", JSON.toJSONString(strategy));
        commonRequest.putQueryParameter("callingNumbers", JSON.toJSONString(callingNumbers));

        CommonResponse response;
		try {
			response = acsClient.getCommonResponse(commonRequest);
			log.info("CreateJobGroup response: " + response.getData());
		} catch (ClientException e) {
			log.error("CreateJobGroup Error: {}",e);
			return "";
		}
        return JSON.parseObject(response.getData()).getJSONObject("JobGroup").getString("Id");

    }

    private static CommonRequest createCommonRequest(String action) {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysAction(action);

        return commonRequest;
    }

	
    public void assignJobs(String jobGroupId, List<JSONObject> jobs) throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("AssignJobs");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.POST);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("groupId", jobGroupId);
        commonRequest.putQueryParameter("jobs", JSONObject.toJSONString(jobs));
        commonRequest.putQueryParameter("callingNumbers", JSONObject.toJSONString(callingNumbers));
        commonRequest.setSysConnectTimeout(120000);
        commonRequest.setSysReadTimeout(120000);

        CommonResponse response = acsClient.getCommonResponse(commonRequest);
        log.info("AssignJobs response:" + response.getData());

    }
    
    public JSONObject listJobsByGroup(String groupId, Integer pageNumber, Integer pageSize){

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("ListJobsByGroup");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("jobGroupId", groupId);
        commonRequest.putQueryParameter("pageNumber", pageNumber+"");
        commonRequest.putQueryParameter("pageSize", pageSize+"");
        CommonResponse response;
		try {
			response = acsClient.getCommonResponse(commonRequest);
			log.info("ListJobsByGroup response: " + response.getData());
		} catch (ClientException e) {
			log.error("ListJobsByGroup Error:{}",e);
			return null;
		}
       
        return JSON.parseObject(response.getData()).getJSONObject("Jobs");
//        {"Status":"Succeeded","ScenarioId":"49ab27be-63c8-4fe4-9436-b39052762f45","Contacts":[{"Honorific":"林先生","ReferenceId":"354bd0e5-557b-4130-a6cf-b5985eca450d","PhoneNumber":"0015018
//        	098431","Id":"4e4056cc-e74d-4a6f-9f7c-ae780ea6fa77","JobUuid":"99050e13-798e-4c89-9424-4687b81c06d0","Name":"林宏宏"},{"Honorific":"刘先生","ReferenceId":"71d2cb71-4ffa-4548-a502-1a40115
//        	bd05e","PhoneNumber":"0013883480818","Id":"d7308280-8c47-4c33-955d-3c1785d05f7d","JobUuid":"99050e13-798e-4c89-9424-4687b81c06d0","Name":"刘浪浪"}],"Priority":5,"SystemPriority":1,"Cr
//        	eateTime":1587873837000,"StrategyId":"a29434e2-8486-4253-8505-493707593291","Id":"99050e13-798e-4c89-9424-4687b81c06d0","Extras":[{"Value":"8711","Key":"ServiceId"},{"Value":"7561"
//        	,"Key":"TenantId"}],"GroupId":"8325a586-3fc5-4353-8449-252320751517","CallingNumbers":["67991101"]}


    }

    
    public JSONObject getJob(String jobId){

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("GetJob");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("jobId", jobId);
        CommonResponse response;
		try {
			response = acsClient.getCommonResponse(commonRequest);
			log.info("GetJob response: " + response.getData());
		} catch (ClientException e) {
			log.error("GetJob Error",e);
			return null;
		}
        
        return JSON.parseObject(response.getData()).getJSONObject("Job");
//        {"RequestId":"612f51de-d2be-4615-b2dc-141c370cd5dd","Job":{"Summary":[],"Gro
//        	upId":"d68df728-31ef-47c5-a665-5cb1ae55ddb7","Extras":[{"Value":"8711","Key":"ServiceId"},{"Value":"7561","Key":"TenantId"}],"Priority":5,"SystemPriority":1,"Ca
//        	llingNumbers":["ALI_OUTBOUND_1101"],"CreateTime":1588175690000,"StrategyId":"94b7d3a3-15fd-40b9-8aee-76ed66c9a6e3","Id":"4364e11e-9f45-4474-b586-906d0c8312af","
//        	ScenarioId":"49ab27be-63c8-4fe4-9436-b39052762f45","Tasks":[{"CallId":"4997d91ccb5a47c0b5b268ab26ffe234","CallingNumber":"ALI_OUTBOUND_1101","ActualTime":158817
//        	5702000,"JobId":"4364e11e-9f45-4474-b586-906d0c8312af","LastUpdated":1588175774000,"ChatbotId":"2374","CreateTime":1588175691000,"Contact":{"PhoneNumber":"13688
//        	436636","JobUuid":"4364e11e-9f45-4474-b586-906d0c8312af","Name":"先生","Id":"9133e906-eb96-458d-9cc5-e3139011fbad","ReferenceId":"fc67a9aa-7b96-4532-86a3-0c63495b
//        	b11b"},"PlanedTime":1588175691000,"EndTime":1588175775000,"Id":"276c2ad9-5fd6-468f-a1a7-66b7defb1af6","ScenarioId":"49ab27be-63c8-4fe4-9436-b39052762f45","Calle
//        	dNumber":"13688436636","Conversation":[{"Speaker":"Contact","Id":"fd0d8816-b465-47d7-ab64-053f4fcd794c","TaskId":"276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script"
//        	:"","Timestamp":1588175702000},{"Speaker":"Robot","Id":"ba57763e-103b-40d5-904d-4c24284befbf","TaskId":"276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"您好，这里是应急
//        	管理部救灾司，就刚才发生的，级地震，向您确认相关情况，请问您是先生本人吗？","Timestamp":1588175703000},{"Speaker":"Contact","Id":"34e04cfd-483d-41fa-94f1-194093e1550b","TaskId":"276c2ad9-5fd6-468f-
//        	a1a7-66b7defb1af6","Script":"那是我。","Timestamp":1588175714000},{"Speaker":"Robot","Id":"b24058af-a7ac-469a-bdd5-71c882649f24","TaskId":"276c2ad9-5fd6-468f-a1a7-6
//        	6b7defb1af6","Script":"好的，请问当时是否有震感呢？","Timestamp":1588175715000},{"Speaker":"Contact","Id":"ecfea52b-5a25-4155-abb7-3cc6540aab8f","TaskId":"276c2ad9-5fd6-468f-
//        	a1a7-66b7defb1af6","Script":"嗯，邮政改。","Timestamp":1588175721000},{"Speaker":"Robot","Id":"70a77552-bdd4-4fc7-8ae2-a58f2e9ab226","TaskId":"276c2ad9-5fd6-468f-a1a7
//        	-66b7defb1af6","Script":"恩，请问当时是否有震感呢？","Timestamp":1588175722000},{"Speaker":"Contact","Id":"527826e2-db07-4303-baf2-476b8e468a27","TaskId":"276c2ad9-5fd6-468f
//        	-a1a7-66b7defb1af6","Script":"有。","Timestamp":1588175727000},{"Speaker":"Robot","Id":"e6d0652a-7549-416f-b703-3f489a4e945d","TaskId":"276c2ad9-5fd6-468f-a1a7-66
//        	b7defb1af6","Script":"好我清楚了，那请问您那边是否有房屋倒塌呢？","Timestamp":1588175727000},{"Speaker":"Contact","Id":"7adde047-31b6-43dc-8cb9-adc474482b7e","TaskId":"276c2ad9-5fd6
//        	-468f-a1a7-66b7defb1af6","Script":"嗯，有的。","Timestamp":1588175735000},{"Speaker":"Robot","Id":"83d65622-8ebd-4f99-a624-a1d2f33e4cd9","TaskId":"276c2ad9-5fd6-468f
//        	-a1a7-66b7defb1af6","Script":"好的，那您那边是否有人员受伤呢？","Timestamp":1588175736000},{"Speaker":"Contact","Id":"df5e47a0-1b3f-4e03-82e0-7a97c0861f04","TaskId":"276c2ad9-5
//        	fd6-468f-a1a7-66b7defb1af6","Script":"没有。","Timestamp":1588175741000},{"Speaker":"Robot","Id":"f80957b8-e603-4386-a234-11062e6abef5","TaskId":"276c2ad9-5fd6-468
//        	f-a1a7-66b7defb1af6","Script":"那您身边是否有人员死亡呢?","Timestamp":1588175741000},{"Speaker":"Contact","Id":"a52d56b2-8c5f-4b9f-bffd-45190875db8b","TaskId":"276c2ad9-5fd
//        	6-468f-a1a7-66b7defb1af6","Script":"有。","Timestamp":1588175746000},{"Speaker":"Robot","Id":"5734eda9-df2f-411b-ae4f-308c034e7962","TaskId":"276c2ad9-5fd6-468f-a
//        	1a7-66b7defb1af6","Script":"好的，那除了上面提到的内容外，您那边是否有公共物资受损？","Timestamp":1588175746000},{"Speaker":"Contact","Id":"ba4388b0-9de0-4e67-ac32-fd20a546746e","TaskId":"
//        	276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"嗯，没有。","Timestamp":1588175754000},{"Speaker":"Robot","Id":"b278fcd3-6c28-4c16-82ed-889095c97505","TaskId":"276c2
//        	ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"我清楚了，请问您那边是否有群众外出避险？","Timestamp":1588175754000},{"Speaker":"Contact","Id":"e4b955dc-5a92-4d55-9662-9590eb277eef","Ta
//        	skId":"276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"有啊。","Timestamp":1588175761000},{"Speaker":"Robot","Id":"8da27895-e21b-446e-91cc-88263f462ae5","TaskId":"
//        	276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"好的，那最后再问一下，您身边还有其他因地震造成的特殊情况吗？","Timestamp":1588175761000},{"Speaker":"Contact","Id":"c5701f07-c726-4a1a-96db-75
//        	b4ec8787f7","TaskId":"276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"没有。","Timestamp":1588175768000},{"Speaker":"Robot","Id":"5ed41396-1392-445c-9516-d2d39fbb4
//        	7f0","TaskId":"276c2ad9-5fd6-468f-a1a7-66b7defb1af6","Script":"好的，感谢您的接听。特殊情况请注意安全，祝您身体健康，再见。","Timestamp":1588175769000}],"Summaries":[],"Status":"Succeeded"}]
//        	,"Contacts":[{"PhoneNumber":"13688436636","JobUuid":"4364e11e-9f45-4474-b586-906d0c8312af","Name":"先生","Id":"9133e906-eb96-458d-9cc5-e3139011fbad","ReferenceId"
//        	:"fc67a9aa-7b96-4532-86a3-0c63495bb11b"}],"Status":"Succeeded"}}

        


    }
    
    //此方法似乎只能查询第一个jobid
    public JSONArray getJobs(List<String> jobIds) throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("GetJobs");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("jobIds", JSON.toJSONString(jobIds));
        CommonResponse response = acsClient.getCommonResponse(commonRequest);
        log.info("GetJobs response: " + response.getData());
        return JSON.parseObject(response.getData()).getJSONArray("Jobs");


    }
    //取消任务
    public JSONObject cancelJobs(String jobGroupId) throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("CancelJobs");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.POST);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("scenarioId", scenarioId);
        commonRequest.putQueryParameter("all", "true");
        commonRequest.putQueryParameter("jobGroupId", jobGroupId);
        CommonResponse response = acsClient.getCommonResponse(commonRequest);
        log.info("CancelJobs response: " + response.getData());
        return JSON.parseObject(response.getData()).getJSONObject("Job");

    }
    
    //暂停任务
    public JSONObject suspendJobs(String jobGroupId) throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("SuspendJobs");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("scenarioId", scenarioId);
        commonRequest.putQueryParameter("all", "true");
        commonRequest.putQueryParameter("jobGroupId", jobGroupId);
        CommonResponse response = acsClient.getCommonResponse(commonRequest);
        log.info("SuspendJobs response: " + response.getData());
        return JSON.parseObject(response.getData()).getJSONObject("Job");

    }
    
    //重启任务 
    public JSONObject resumeJobs(String jobGroupId) throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("ResumeJobs");
        commonRequest.setSysVersion("2019-12-26");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);

        commonRequest.putQueryParameter("instanceId", instanceId);
        commonRequest.putQueryParameter("scenarioId", scenarioId);
        commonRequest.putQueryParameter("all", "true");
        commonRequest.putQueryParameter("jobGroupId", jobGroupId);
        CommonResponse response = acsClient.getCommonResponse(commonRequest);
        log.info("ResumeJobs response: " + response.getData());
        return JSON.parseObject(response.getData()).getJSONObject("Job");

    }
    
    
    public JSONArray getConversationTags(String sessionId, String chatbotId){

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysAction("GetConversationTags");
        commonRequest.setSysVersion("2017-10-11");
        commonRequest.setSysProduct("Chatbot");
        commonRequest.setSysMethod(MethodType.GET);
        //2374
        commonRequest.putQueryParameter("InstanceId", chatbotId);
        commonRequest.putQueryParameter("SessionId", sessionId);
        CommonResponse response;
		try {
			response = acsClient.getCommonResponse(commonRequest);
			log.info("GetConversationTags response: " + response.getData());
		} catch (ClientException e) {
            try {
                response=acsClient.getCommonResponse(commonRequest);
            } catch (ClientException ex) {
                log.error("GetConversationTags Error:{}",e);
                return null;
            }

		}
		 return JSON.parseObject(response.getData()).getJSONArray("Tags");
    }
}
