-- 기존 내용은 모두 지우고 아래 내용으로 테스트해보세요.

-- 1. User 데이터 삽입
-- 비밀번호는 원래 BCrypt 등으로 해싱해서 넣어야 하지만, 테스트용으로 일단 평문을 넣습니다.
INSERT INTO users (username, password) VALUES ('wonji', '1234');
INSERT INTO users (username, password) VALUES ('testuser', '1234');

