package com.ispong.oxygen.websockets.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * websocket 对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
public class WebSocketBean {

    /**
     * websocket session
     */
    private WebSocketSession webSocketSession;
}
