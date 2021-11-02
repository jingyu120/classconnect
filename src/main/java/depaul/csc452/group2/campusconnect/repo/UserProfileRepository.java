package depaul.csc452.group2.campusconnect.repo;

import depaul.csc452.group2.campusconnect.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, Long> {
}
