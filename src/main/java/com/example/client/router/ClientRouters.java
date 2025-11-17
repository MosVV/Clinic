package com.example.client.router;

import com.example.base.routes.BaseRouter;

public class ClientRouters {
    private final static String ROOT = BaseRouter.API + "/client";
    public static final String REGISTRATION = BaseRouter.NOT_SECURED + "/v1/registration";
    public static final String ME = ROOT + "/me";
}
