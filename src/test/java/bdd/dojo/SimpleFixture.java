package bdd.dojo;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static bdd.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;

public class SimpleFixture {

    private SimpleDriver driver;

    @Test
    public void test() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("simple.story")
                .run();
    }

    @When("we put a message on the inbound queue")
    public void whenWePutAMessageOnTheInboundQueue() {
    }

    @Then("we put the message into the database in the right form")
    public void thenWePutTheMessageIntoTheDatabaseInTheRightForm() {
    }
}
