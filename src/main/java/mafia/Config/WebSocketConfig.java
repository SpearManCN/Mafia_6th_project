package mafia.Config;

import mafia.Controller.MyHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

// 웹소켓의 메세지 브로커 환경설정
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override // 메세지 브로커 설정
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic","/totUser");
        // 메세지 브로커를 만들어준것.
        config.setApplicationDestinationPrefixes("/app");
        // 해당 접두사가 있으면 접두사 이후의 주소를 socketController에서 처리해준다.
        // 딱히 기능은 없지만 구분하기위한 접두사.
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").addInterceptors(new HttpSessionHandshakeInterceptor(), new MyHandshakeInterceptor()).setAllowedOrigins("*"); // SockJS를 사용하지 않음
        // /chat으로 시작하는 stomp를 등록. 해당 stomp를 socket으로 연결함으로써 통신시작.
    }
//registry.addEndpoint("/chat"): 이 부분은 /chat라는 엔드포인트를 등록하는 설정입니다. 클라이언트는 이 엔드포인트를 사용하여 WebSocket에 연결합니다.
//            .addInterceptors(new HttpSessionHandshakeInterceptor(), new MyHandshakeInterceptor()): 이 부분은 핸드셰이크 인터셉터를 등록하는 설정입니다. 핸드셰이크 인터셉터는 WebSocket 연결이 수립될 때 추가 작업을 수행할 수 있도록 해줍니다. 여기서는 기본적으로 제공되는 HttpSessionHandshakeInterceptor와 커스텀 인터셉터 MyHandshakeInterceptor를 함께 등록합니다.
//.setAllowedOrigins("*"): 이 부분은 WebSocket 연결을 허용할 origin을 설정하는 것입니다. 여기서는 모든 origin을 허용하기 위해 "*"를 설정했습니다. 이렇게 함으로써 모든 클라이언트가 WebSocket에 연결할 수 있습니다.
}
