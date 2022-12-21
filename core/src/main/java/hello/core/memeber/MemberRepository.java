package hello.core.memeber;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
