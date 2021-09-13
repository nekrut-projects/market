package gb.market;

import gb.market.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setTitle("TEST");
        assertThat(jackson.write(role))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("TEST");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"title\":\"TEST\"}";
        Role realRole = new Role();
        realRole.setId(2);
        realRole.setTitle("TEST");

        assertThat(jackson.parse(content)).isEqualTo(realRole);
        assertThat(jackson.parseObject(content).getTitle()).isEqualTo("TEST");
    }
}