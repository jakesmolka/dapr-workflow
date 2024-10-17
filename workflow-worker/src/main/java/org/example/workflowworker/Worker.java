package org.example.workflowworker;

import io.dapr.workflows.runtime.WorkflowRuntime;
import io.dapr.workflows.runtime.WorkflowRuntimeBuilder;
import org.example.flows.CreateResourceActivity;
import org.example.flows.GetResourceActivity;
import org.example.flows.ToUpperCaseActivity;
import org.example.flows.createdemographicresource.CreateDemographicResourceWorkflow;
import org.example.flows.getdemographicresource.GetDemographicResourceWorkflow;
import org.springframework.stereotype.Component;

@Component
public class Worker {

    public Worker() {
        // Register the Workflow with the builder.
        WorkflowRuntimeBuilder builder = new WorkflowRuntimeBuilder()
                .registerWorkflow(GetDemographicResourceWorkflow.class)
                .registerWorkflow(CreateDemographicResourceWorkflow.class);

        builder.registerActivity(ToUpperCaseActivity.class);
        builder.registerActivity(CreateResourceActivity.class);
        builder.registerActivity(GetResourceActivity.class);

        // Build and then start the workflow runtime pulling and executing tasks
        try (WorkflowRuntime runtime = builder.build()) {
            System.out.println("Start workflow runtime");
            runtime.start();
        }
    }
}