package com.org.custom_springboot_template.service.concrete;

import com.org.custom_springboot_template.product.dto_convertor.primary_convertor.UserDtoConvertor;
import com.org.custom_springboot_template.repository.UserDao;
import com.org.custom_springboot_template.service.abstracts.ILogUserService;
import com.org.custom_springboot_template.service.abstracts.IRoleService;
import com.org.custom_springboot_template.service.abstracts.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements IUserService {

    private final String logTitle = "UserService : -> ";

    private final UserDao userRepository;
    private final UserDtoConvertor userDtoConvertor;
    private final ILogUserService logUserService;
    private final IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserService(UserDao userRepository,
                       UserDtoConvertor userDtoConvertor,
                       ILogUserService logUserService,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.userDtoConvertor = userDtoConvertor;
        this.logUserService = logUserService;
        this.roleService = roleService;
    }

    /*
    @Override
    public UserDto addUser(UserRequestCreate requestCreate) {

        Role userRole = roleService.getRoleByRoleName(AppEnumRoleTypes.USER).getData();

        HashSet<Role> roles = new HashSet<>();
        roles.add(userRole);

        if (util_isNotExistUserByEmail(requestCreate.getEmail()) &&
                util_isNotExistUserByUserNickName(requestCreate.getNickName())) {
            User user = new User(
                    null,
                    requestCreate.getNickName(),
                    requestCreate.getEmail(),
                    passwordEncoder.encode(requestCreate.getPassword()),
                    true,
                    false,
                    roles,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            User userFound = userDao.save(user);

            addSignUpLogToUser(null, userFound);

            UserDto userDto = userDtoConvertor.convert(userFound);

            log.info(logTitle + "user created successfully");

            return userDto;
        } else {

            log.error(logTitle + " user not created already created");

            throw new AlReadyExistException(CoreEnumExceptionMessages.ALREADY_EXIST_USER, "user already created");
        }

    }

    @Override
    public UserDto getUserById(String id) {

        return userDtoConvertor.convert(util_getUserById(id));
    }

    @Override
    public UserDto getUserByNickName(String userNickName) {

        User userFound = util_getUserByNickNameOrEmail(userNickName, null);

        UserDto userDto = userDtoConvertor.convert(userFound);

        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User userFound = util_getUserByNickNameOrEmail(null, email);

        if (userFound == null) {

            throw new NotFoundException(NOT_FOUND_USER, "cant fetched user by email");
        } else {
            return userDtoConvertor.convert(userFound);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return  userDao.findAll()
                .stream().map(user -> userDtoConvertor.convert(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUserById(String id, UserRequestUpdate requestUpdate) {

        User foundUser = userDao.findById(id).get();
        foundUser.setEmail(requestUpdate.getEmail());
        foundUser.setPassword(requestUpdate.getPassword());
        foundUser.setNickName(requestUpdate.getNickName());
        foundUser.setUpdatedDate(LocalDateTime.now());

        return userDtoConvertor.convert(userDao.save(foundUser));
    }

    @Override
    public Boolean deleteUserById(String id) {
        User user = userDao.getById(id);
        userDao.delete(user);
        return true;
    }


    @Override
    public DataResult<NoteDto> addNoteToUser(NoteRequestCreate requestCreate) {
        return null;
    }

    @Override
    public DataResult<AddressDto> addAddressToUser(AddressRequestCreate requestCreate) {
        return null;
    }


    //LOG
    @Override
    public DataResult addLogInLogToUser(@Nullable String userNickName, @Nullable User user) {
        if (userNickName != null) {

            log.info(logTitle + "login log added to user by user nickname");

            return util_addLogToUser(userNickName, new LogUserRequestCreate(LOG_IN));
        } else {

            log.info(logTitle + "login log added to user by user");

            return util_addLogToUser(user, new LogUserRequestCreate(LOG_IN));

        }

    }

    @Override
    public DataResult addLogOutLogToUser(@Nullable String userNickName, @Nullable User user) {

        if (userNickName != null) {

            log.info(logTitle + "logout log added to user by user nickname");

            return util_addLogToUser(userNickName, new LogUserRequestCreate(LOG_OUT));
        } else {

            log.info(logTitle + "logout log added to user by user");

            return util_addLogToUser(user, new LogUserRequestCreate(LOG_OUT));

        }
    }

    @Override
    public DataResult addSignUpLogToUser(@Nullable String userNickName, @Nullable User user) {
        if (userNickName != null) {

            log.info(logTitle + "signup log added to user by user nickname");

            return util_addLogToUser(userNickName,
                    new LogUserRequestCreate(AppEnumOperationTypes.CREATED),
                    new LogUserRequestCreate(SIGN_UP),
                    new LogUserRequestCreate(LOG_IN));
        } else {

            log.info(logTitle + "signup log added to user by user");

            return util_addLogToUser(user,
                    new LogUserRequestCreate(AppEnumOperationTypes.CREATED),
                    new LogUserRequestCreate(SIGN_UP),
                    new LogUserRequestCreate(LOG_IN));
        }

    }

    @Override
    public DataResult addRegisteredLogToUser(@Nullable String userNickName, @Nullable User user) {

        if (userNickName != null) {

            log.info(logTitle + "register log added to user by user nickname");

            return util_addLogToUser(userNickName, new LogUserRequestCreate(REGISTERED));
        } else {

            log.info(logTitle + "register log added to user by user");

            return util_addLogToUser(user, new LogUserRequestCreate(REGISTERED));

        }
    }

    @Override
    public Boolean setConfirmedUser(User user) {

        user.setIsRegistered(true);
        user.setUpdatedDate(LocalDateTime.now());
        User userFound = userDao.save(user);
        addRegisteredLogToUser(null, user);

        log.info(logTitle + "user confirmed by user");

        return userFound != null;
    }

    @Override
    public Boolean verifyUserPassword(User user, String password) {

        if (passwordEncoder.matches(password,user.getPassword())) {

            log.info(logTitle + " user password verified");

            return true;
        } else {

            log.error(logTitle + "user password not matched with sent password");

            throw new UnAcceptableException(UN_ACCEPTABLE_USER_PASSWORD, "user password not matched with sent password");
        }
    }

    //****************************************************************/
/*
    //UTIL
    private DataResult util_addLogToUser(User user, LogUserRequestCreate... logUserRequestCreates) {

        if (user != null) {

            log.info(logTitle + "log list adding to user by user");

            for (LogUserRequestCreate logUserRequestCreate : logUserRequestCreates) {
                logUserService
                        .addLogUser(logUserRequestCreate, user);
            }

        } else {

            log.error(logTitle + "log cant add to user by user");

            throw new NotFoundException(NOT_FOUND_USER, "cant add log to user");
        }

        log.info(logTitle + "log added to successfully to user");

        return new SuccessDataResult<>(userDtoConvertor.convert(user),
                user.getNickName() + "log added successfully to user");
    }


    private DataResult util_addLogToUser(String userNickName, LogUserRequestCreate... logUserRequestCreates) {

        User user = userDao.getUserByNickName(userNickName);

        log.info(logTitle + "log list adding to user by user nick name");

        if (user != null) {

            for (LogUserRequestCreate logUserRequestCreate : logUserRequestCreates) {
                logUserService
                        .addLogUser(logUserRequestCreate, user);
            }

        } else {

            log.error(logTitle + "log cant add to user by user");

            throw new NotFoundException(NOT_FOUND_USER, "cant add log to user");
        }

        log.info(logTitle + "log added to successfully to user");

        return new SuccessDataResult<>(userDtoConvertor.convert(user),
                userNickName + "log added successfully to user");
    }

    private User util_getUserById(String userId) {
        User userFound = userDao.getById(userId);

        if (userFound != null) {

            log.info(logTitle + "user found by user id");

            return userFound;
        } else {

            log.error(logTitle + "user not found by user id");

            throw new NotFoundException(NOT_FOUND_USER, "user not found by ıd");
        }
    }

    private User util_getUserByNickNameOrEmail(@Nullable String userNickName, @Nullable String email) {

        User userFound;

        if (userNickName != null) {
            userFound = userDao.getUserByNickName(userNickName);

            if (userFound != null) {

                log.info(logTitle + "user found by user nick name");

                return userFound;
            } else {

                log.error(logTitle + " user not found by user nick name");

                throw new NotFoundException(NOT_FOUND_USER, "not found user by user nick name");
            }

        } else if (email != null) {
            userFound = userDao.getUserByEmail(email);

            if (userFound != null) {

                log.info(logTitle + "user found by email");

                return userFound;
            } else {

                log.error(logTitle + "user not found email");

                throw new NotFoundException(NOT_FOUND_USER, "not found user created by email");
            }

        } else if (userNickName != null && email != null) {

            userFound = userDao.getUserByNickNameOrEmail(userNickName, email);

            if (userFound != null) {

                log.info(logTitle + "user found by user nick name and email");

                return userFound;
            } else {

                log.error(logTitle + "user not found by user nick name and email");

                throw new NotFoundException(NOT_FOUND_USER, "not found user by user nick name and  email");
            }
        } else {

            log.error(logTitle + "user not found");

            throw new NotFoundException(NOT_FOUND_USER, "not found user by nothing parameter");
        }
    }

    private Boolean util_isNotExistUserById(String userId) {

        if (userDao.getById(userId) != null) {

            log.error(logTitle + "user exist already by ıd");

            throw new AlReadyExistException(ALREADY_EXIST_USER, "already exist user by id");
        } else {

            log.info(logTitle + "user not exist already by ıd");

            return true;
        }
    }

    private Boolean util_isNotExistUserByEmail(String email) {

        if (userDao.getUserByEmail(email) != null) {

            log.error(logTitle + "user exist already by email");

            throw new AlReadyExistException(ALREADY_EXIST_USER, "already exist user by email");
        } else {

            log.info(logTitle + "user not exist already by email");

            return true;
        }
    }

    private Boolean util_isNotExistUserByUserNickName(String userNickName) {

        if (userDao.getUserByNickName(userNickName) != null) {

            log.error(logTitle + "user exist already by user nickname");

            throw new AlReadyExistException(ALREADY_EXIST_USER, "already exist user by nick name");
        } else {

            log.info(logTitle + "user not exist already by user nickname");

            return true;
        }
    }
*/
}
