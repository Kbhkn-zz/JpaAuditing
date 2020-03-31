package com.kbhkn.jpademo.configurations.eventlistener;

import com.kbhkn.jpademo.domain.ActionEntity;
import com.kbhkn.jpademo.domain.RoleEntity;
import com.kbhkn.jpademo.domain.UserEntity;
import com.kbhkn.jpademo.repository.ActionRepository;
import com.kbhkn.jpademo.repository.RoleRepository;
import com.kbhkn.jpademo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

/**
 * @author Hakan KABASAKAL, 31-Mar-20
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class StartupProcess {
    private final ActionRepository actionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    /*
    * Load users.
    * Or it can be use "data.sql" in src/main/resource.
    *
    * And active spring.datasource.initialization-mode=always
    *
    * */
    @EventListener(ContextRefreshedEvent.class)
    public void load() {

        ActionEntity action1 = new ActionEntity();
        action1.setUrl("/abc1");

        ActionEntity action2 = new ActionEntity();
        action2.setUrl("/abc2");

        ActionEntity action3 = new ActionEntity();
        action3.setUrl("/abc3");

        ActionEntity action4 = new ActionEntity();
        action4.setUrl("/abc4");

        ActionEntity action5 = new ActionEntity();
        action5.setUrl("/nonSecure");

        actionRepository.saveAll(Arrays.asList(action1, action2, action3, action4, action5));

        RoleEntity role1 = new RoleEntity();
        role1.setCode("ROLE_ADMIN");
        role1.setName("Admin");
        role1.getActions().addAll(Arrays.asList(action1, action2, action3, action4));

        RoleEntity role2 = new RoleEntity();
        role2.setCode("ROLE_USER");
        role2.setName("Normal User");
        role2.getActions().addAll(Arrays.asList(action2, action3));

        RoleEntity role3 = new RoleEntity();
        role3.setCode("ROLE_NON_SECURE_USER");
        role3.setName("Non Secure User");
        role3.getActions().add(action5);

        roleRepository.saveAll(Arrays.asList(role1, role2, role3));


        UserEntity user1 = new UserEntity();
        user1.setUsername("Admin");
        user1.setToken("adminToken_jwt");
        user1.setIsActive('Y');
        user1.getRoles().addAll(Arrays.asList(role1, role3));

        UserEntity user2 = new UserEntity();
        user2.setUsername("User");
        user2.setToken("userToken_jwt");
        user2.setIsActive('Y');
        user2.getRoles().add(role2);

        userRepository.saveAll(Arrays.asList(user1, user2));

        log.info("Dummy values are loaded.");
    }
}
