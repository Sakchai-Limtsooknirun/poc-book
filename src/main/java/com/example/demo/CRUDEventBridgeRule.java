package com.scb.pointx.order;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.*;

import java.util.ArrayList;
import java.util.List;

public class CRUDEventBridgeRule {

    public static void main(String[] args) {


        String ruleName = "jaytestTestAddTargets";
        Region region = Region.AP_SOUTHEAST_1;
        String lambdaId = "testInvolkFromEventBridgeRule";
        String lambdaArn = "arn:aws:lambda:ap-southeast-1:572861332609:function:testInvolkFromEventBridgeRule";
        EventBridgeClient eventBrClient = EventBridgeClient.builder()
                .region(region)
                .build();

        PutRuleResponse createResponse = createEBRule(eventBrClient, ruleName);
//        putEBEvents(eventBrClient, createResponse.ruleArn());
//        listAllRules(eventBrClient);
        DescribeRuleResponse describeSpecificRule = describeSpecificRule(eventBrClient, ruleName);
        putTargets(eventBrClient, createResponse.ruleArn(), ruleName, lambdaArn, lambdaId, describeSpecificRule.roleArn());
//        deleteEBRule(eventBrClient, ruleName);
        eventBrClient.close();
    }

    public static PutRuleResponse createEBRule(EventBridgeClient eventBrClient, String ruleName) {

        try {

            PutRuleRequest ruleRequest = PutRuleRequest.builder()
                    .name(ruleName)
                    .eventBusName("default")
                    .description("A test rule created by the Java API")
                    .scheduleExpression("rate(1 minute)")
                    .build();

            PutRuleResponse ruleResponse = eventBrClient.putRule(ruleRequest);
            System.out.println("The ARN of the new rule is " + ruleResponse.ruleArn());
            return ruleResponse;

        } catch (EventBridgeException e) {

            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
            return null;
        }
    }

    public static void putTargets(EventBridgeClient eventBrClient, String resourceArn, String ruleName, String targetToTriggerArn, String targetToTriggerId, String roleArn) {
        //TODO lambda ต้อง add trigger permission จาก event-bridge ใหม่เสมอ /????????
        try {
            // Populate a List with the resource ARN values
            List<String> resources = new ArrayList<String>();
            resources.add(resourceArn);
//            resources.add(resourceArn2);

            Target lambdaTarget = Target.builder()
                    .arn(targetToTriggerArn) //Lambda Arn
                    .roleArn(roleArn) // ไม่ใช้ก้อได้
                    .id(targetToTriggerId) //Lambda Id
                    .input("{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}")
                    .build();

//            Target httpAPIGatewayTarget = Target.builder()
////                    .arn(targetToTriggerArn) ////arn:aws:execute-api
////                    .roleArn(roleArn) ////API Gateway Id
////                    .id(targetToTriggerId) ////API Gateway Id
//                    .httpParameters()
//                    .input("{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}")
//                    .build();

            PutTargetsRequest eventsRequest = PutTargetsRequest.builder()
                    .targets(lambdaTarget)
//                    .targets(httpAPIGatewayTarget)
                    .rule(ruleName)
                    .build();

            PutTargetsResponse result = eventBrClient.putTargets(eventsRequest);

            for (PutTargetsResultEntry resultEntry : result.failedEntries()) {
                if (resultEntry.targetId() != null) {
                    System.out.println("target Id: " + resultEntry.targetId());
                } else {
                    System.out.println("Injection failed with Error Code: " + resultEntry.errorCode());
                }
            }

        } catch (EventBridgeException e) {

            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }


    public static DescribeRuleResponse describeSpecificRule(EventBridgeClient eventBrClient, String ruleName) {

        try {

            DescribeRuleRequest ruleRequest = DescribeRuleRequest.builder()
                    .name(ruleName)
                    .eventBusName("default")
                    .build();

            DescribeRuleResponse ruleResponse = eventBrClient.describeRule(ruleRequest);

            System.out.println("The rule ARN is " + ruleResponse.arn());
            System.out.println("The role ARN is " + ruleResponse.roleArn());
            System.out.println("The scheduleExpression is " + ruleResponse.scheduleExpression());
            System.out.println("The eventPattern is " + ruleResponse.eventPattern());
            System.out.println("The responseMetadata is " + ruleResponse.responseMetadata());
            return ruleResponse;

        } catch (EventBridgeException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }


    public static void listAllRules(EventBridgeClient eventBrClient) {

        try {

            ListRulesRequest rulesRequest = ListRulesRequest.builder()
                    .eventBusName("default")
                    .limit(10)
                    .build();

            ListRulesResponse response = eventBrClient.listRules(rulesRequest);
            List<Rule> rules = response.rules();

            for (Rule rule : rules) {
                System.out.println("The rule name is : " + rule.name());
                System.out.println("The rule ARN is : " + rule.arn());
            }

        } catch (EventBridgeException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public static void putEBEvents(EventBridgeClient eventBrClient, String resourceArn) {

        try {
            // Populate a List with the resource ARN values
            List<String> resources = new ArrayList<String>();
            resources.add(resourceArn);
//            resources.add(resourceArn2);

            PutEventsRequestEntry reqEntry = PutEventsRequestEntry.builder()
                    .resources(resources)
                    .source("com.mycompany.myapp")
                    .detailType("myDetailType")
                    .detail("{ \"key1\": \"value1\", \"key2\": \"value2\" }")
                    .build();

            // Add the PutEventsRequestEntry to a list
            List<PutEventsRequestEntry> list = new ArrayList<PutEventsRequestEntry>();
            list.add(reqEntry);

            PutEventsRequest eventsRequest = PutEventsRequest.builder()
                    .entries(reqEntry)
                    .build();

            PutEventsResponse result = eventBrClient.putEvents(eventsRequest);

            for (PutEventsResultEntry resultEntry : result.entries()) {
                if (resultEntry.eventId() != null) {
                    System.out.println("Event Id: " + resultEntry.eventId());
                } else {
                    System.out.println("Injection failed with Error Code: " + resultEntry.errorCode());
                }
            }

        } catch (EventBridgeException e) {

            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }


    public static void deleteEBRule(EventBridgeClient eventBrClient, String ruleName) {

        try {

            // Disable the rule - an Enabled Rule cannot be deleted
            DisableRuleRequest disableRuleRequest = DisableRuleRequest.builder()
                    .name(ruleName)
                    .eventBusName("default")
                    .build();

            eventBrClient.disableRule(disableRuleRequest);

            DeleteRuleRequest ruleRequest = DeleteRuleRequest.builder()
                    .name(ruleName)
                    .eventBusName("default")
                    .build();

            eventBrClient.deleteRule(ruleRequest);
            System.out.println("Rule " + ruleName + " was successfully deleted!");

        } catch (EventBridgeException e) {

            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }


}