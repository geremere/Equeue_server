package ru.hse.equeue.util;

public final class EndPoints {

    public static final String SWAGGER = "/swagger-ui/**";

    public static final String SWAGGER_UTIL = "/v3/api-docs/**";

    public static final String BASE = "/api";

    public static final String BASE_USER = BASE + "/user";

    public static final String GET = "/get";

    public static final String BY_ID = "/{id}";

    public static final String SIGN_IN = BASE + "/signin";

    public static final String AUTH = BASE + "/auth/{token}";

    public static final String GET_CURRENT_USER = BASE_USER + GET;

    public static final String USER_BY_ID = BASE_USER + BY_ID;

    public static final String BASE_QUEUE = BASE + "/queue";

    public static final String QUEUE_BY_ID = BASE_QUEUE + BY_ID;

    public static final String QUEUE_LIST = BASE_QUEUE + "/list";

}
