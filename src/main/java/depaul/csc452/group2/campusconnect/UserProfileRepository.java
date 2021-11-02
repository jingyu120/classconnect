package depaul.csc452.group2.campusconnect;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, Long> {
}
