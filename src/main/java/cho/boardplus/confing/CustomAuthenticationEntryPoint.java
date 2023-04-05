package cho.boardplus.confing;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}


/*
해당 코드는 스프링 시큐리티에서 인증되지 않은 사용자가 보호된 리소스에 접근할 때 호출되는 commence() 메서드입니다.
먼저 HttpServletResponse.SC_UNAUTHORIZED 를 사용하여 인증되지 않은 오류를 응답합니다. 그런 다음,
XMLHttpRequest 헤더가 있는 경우 AJAX 요청을 처리하기 위해 동일한 HttpServletResponse.SC_UNAUTHORIZED 응답을 다시 보내고,
그렇지 않은 경우 /members/login 으로 리디렉션합니다.
즉, 이 코드는 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때 적절한 오류 응답을 보내거나 로그인 페이지로 리디렉션하여 인증을 요구합니다.

 */