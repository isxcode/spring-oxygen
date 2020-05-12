//package com.ispong.oxygen.websockets.stomp;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
//@Slf4j
//@Controller
//public class TestStompController {
//
//    private SimpMessagingTemplate template;
//
//    @Autowired
//    public TestStompController(SimpMessagingTemplate template) {
//
//        this.template = template;
//    }
//
//    @GetMapping("/ws/greet")
//    public void greet(@RequestParam("greeting") String greeting) {
//
//        People people = new People();
//        people.setName("hello");
//        this.template.convertAndSend("/topic/greetings", people);
//
//    }
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public void greeting(Map<String, String> request) throws Exception {
//
//        log.info("推送到这个接口" + request);
////        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
//
//}