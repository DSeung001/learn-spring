package hello.core.memeber;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
