package com.ogms.dsc.domain.constants;

import java.io.File;
import java.nio.file.Paths;

/**
 * @name: Constants
 * @description: 常量
 * @author: Lingkai Shi
 * @date: 5/6/2024 10:16 PM
 * @version: 1.0
 */
public class Constants {
    public static final String SESSION_KEY = "session_key";
    public static final String CHECK_CODE_KEY = "check_code_key";

    public static final String JWT_ISSUER = "OGMS";

    // 用于签名加密的密钥，为一个字符串（需严格保密）
    public static final String JWT_KEY = "jwt_key";

    public static final String ROOT_PATH = System.getProperty("user.dir");

    public static final String GRAPH_JSON_PATH = ROOT_PATH + File.separator + "assets" + File.separator + "graphJson";

    public static final String RESOURCE_UPLOAD_PATH = ROOT_PATH + File.separator + "assets" + File.separator + "resource";

    public static final String AVATAR_PATH = ROOT_PATH + File.separator + "assets" + File.separator + "avatar";

    public static final String MAPLOGO_PATH = ROOT_PATH + File.separator + "assets" + File.separator + "mapLogo";

    public static final String SCRIPT_PATH = String.valueOf(Paths.get(ROOT_PATH, "src", "main", "resources", "scripts"));

    // The Python Interpreter on Windows
    public static final String PYTHON_INTERPRETER_PATH = "C:\\Users\\lkshi\\.conda\\envs\\Python38\\python.exe";
}
