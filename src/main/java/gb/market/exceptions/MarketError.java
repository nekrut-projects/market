package gb.market.exceptions;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MarketError {
    private List<String> messages;
    private Date timestamp;

    public MarketError(String message) {
        this(Arrays.asList(message));
    }

    public MarketError(String... messages){
        this(Arrays.asList(messages));
    }

    public MarketError(List<String> messages) {
        this.messages = new ArrayList<>(messages);
        this.timestamp = new Date();
    }
}
