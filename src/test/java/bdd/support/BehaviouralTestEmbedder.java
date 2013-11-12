package bdd.support;


import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.configuration.spring.SpringStoryReporterBuilder;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.junit.Assert.assertThat;

public class BehaviouralTestEmbedder extends ConfigurableEmbedder {

    private static final Logger LOG = LoggerFactory.getLogger(BehaviouralTestEmbedder.class);
    public static final String BAD_USE_OF_API_MESSAGE = "You are trying to set the steps factory twice ... this is a paradox";

    private String wildcardStoryFilename;
    private InjectableStepsFactory stepsFactory;


    private BehaviouralTestEmbedder() {
    }

    public static BehaviouralTestEmbedder aBehaviouralTestRunner() {
        return new BehaviouralTestEmbedder();
    }

    @Override
    public void run() throws Exception {
        List<String> paths = createStoryPaths();
        if (paths == null || paths.isEmpty()) {
            throw new IllegalStateException("No story paths found for state machine");
        }
        LOG.info("Running [" + this.getClass().getSimpleName() + "] with spring_stories [" + paths + "]");
        configuredEmbedder().runStoriesAsPaths(paths);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        assertThat(stepsFactory, is(notNullValue()));
        return stepsFactory;
    }

    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromURL())
                .useParameterConverters(new ParameterConverters().addConverters(new SandboxDateConverter()))
                .useStoryReporterBuilder(new SandboxStoryReporterBuilder());
    }

    private List<String> createStoryPaths() {
        return ClasspathStoryFinder.findFilenamesThatMatch(wildcardStoryFilename);
    }

    public BehaviouralTestEmbedder withStory(String aWildcardStoryFilename) {
        wildcardStoryFilename = aWildcardStoryFilename;
        return this;
    }

    public BehaviouralTestEmbedder usingStepsFrom(Object... stepsSource) {
        assertThat(BAD_USE_OF_API_MESSAGE, stepsFactory, is(nullValue()));
        stepsFactory = new InstanceStepsFactory(configuration(), stepsSource);
        return this;
    }

    public BehaviouralTestEmbedder usingStepsConfigurationContextFrom(Class<?>... someContextClasses) {
        assertThat(BAD_USE_OF_API_MESSAGE, stepsFactory, is(nullValue()));
        stepsFactory = new SpringStepsFactory(configuration(), new AnnotationConfigApplicationContext(someContextClasses));
        return this;
    }

    public BehaviouralTestEmbedder usingStepsXmlContextFrom(String... someContextResources) {
        assertThat(BAD_USE_OF_API_MESSAGE, stepsFactory, is(nullValue()));
        stepsFactory = new SpringStepsFactory(configuration(), new ClassPathXmlApplicationContext(someContextResources));
        return this;
    }

    private static class SandboxDateConverter extends ParameterConverters.DateConverter {

        public SandboxDateConverter() {
            super(new SimpleDateFormat("dd-MM-yyyy"));
        }
    }

    private static class SandboxStoryReporterBuilder extends SpringStoryReporterBuilder {

        public SandboxStoryReporterBuilder() {
            withCodeLocation(codeLocationFromClass(SandboxStoryReporterBuilder.class));
            withDefaultFormats();
            withFormats(HTML, CONSOLE);
            withFailureTrace(true);
        }
    }

}
