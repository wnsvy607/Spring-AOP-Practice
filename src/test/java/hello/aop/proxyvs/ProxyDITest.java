package hello.aop.proxyvs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Import(ProxyDIAspect.class)
// @SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK 동적 프록시
// @SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB 프록시
/**
 * spring.aop.proxy-target-class=true 가 기본값이기 때문에 CGLIB가 기본으로 적용된다.
 * CGLIB은 단점이 있다.
 * 1. 대상 클래스의 기본 생성자 필수
 * 2. 대상 클래스의 생성자 2번 호출
 * 3. final 키워드가 붙은 클래스, 메서드에서 적용 불가
 * 1, 2번째 장점은 해소가 되었고 일반적인 웹개발에서 final 키워드는 잘 사용하지 않으므로
 * CGLIB 를 사용해도 된다.
 */
@SpringBootTest
public class ProxyDITest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberServiceImpl memberServiceImpl;

	@Test
	void go() {
		log.info("memberService class={}", memberService.getClass());
		log.info("memberServiceImpl calss={}", memberServiceImpl.getClass());
		memberService.hello("hello");
	}
}
