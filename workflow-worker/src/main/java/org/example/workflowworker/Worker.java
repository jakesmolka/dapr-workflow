package org.example.workflowworker;

import io.dapr.workflows.runtime.WorkflowRuntime;
import io.dapr.workflows.runtime.WorkflowRuntimeBuilder;
import org.example.flows.ToUpperCaseActivity;
import org.example.flows.GetDemographicResourceWorkflow;
import org.springframework.stereotype.Component;

@Component
public class Worker {

    public Worker() {
        // Register the Workflow with the builder.
        WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder().registerWorkflow(GetDemographicResourceWorkflow.class);
        builder.registerActivity(ToUpperCaseActivity.class);

        // Build and then start the workflow runtime pulling and executing tasks
        try (WorkflowRuntime runtime = builder.build()) {
            System.out.println("Start workflow runtime");
            runtime.start();
        }
    }
}