package com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
