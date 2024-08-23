package org.example.workflowconnector;

import io.dapr.workflows.client.DaprWorkflowClient;
import io.dapr.workflows.client.WorkflowInstanceStatus;
import org.example.flows.GetDemographicResourceWorkflow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RestController
public class Controller {

    @GetMapping("/patient/{id}")
    public String getPatient(@PathVariable String id) throws InterruptedException, TimeoutException {

        try (DaprWorkflowClient workflowClient = new DaprWorkflowClient()) {
            String instanceId = workflowClient.scheduleNewWorkflow(GetDemographicResourceWorkflow.class, "Patient/" + id);
            System.out.printf("Started a new chaining model workflow with instance ID: %s%n", instanceId);
            WorkflowInstanceStatus workflowInstanceStatus =
                    workflowClient.waitForInstanceCompletion(instanceId, null, true);

            String result = workflowInstanceStatus.readOutputAs(String.class);
            System.out.printf("workflow instance with ID: %s completed with result: %s%n", instanceId, result);

            return result;
        }
    }
}
