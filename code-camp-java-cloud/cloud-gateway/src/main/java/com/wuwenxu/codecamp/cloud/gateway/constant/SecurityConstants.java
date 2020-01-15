package com.wuwenxu.codecamp.cloud.gateway.constant;

/**
 * @author zhangquan
 * @date 2019/5/21
 */
public interface SecurityConstants {
  /**
   * 角色前缀
   */
  String ROLE           = "ROLE_";
  /**
   * 前缀
   */
  String PROJECT_PREFIX = "yunli_";

  /**
   * oauth 相关前缀
   */
  String OAUTH_PREFIX    = "oauth:";
  /**
   * 项目的license
   */
  String PROJECT_LICENSE = "made by yunli";

  /**
   * 内部
   */
  String FROM_IN = "Y";

  /**
   * 标志
   */
  String FROM = "from";

  /**
   * 手机号登录URL
   */
  String MOBILE_TOKEN_URL = "/mobile/token";

  /**
   * 默认登录URL
   */
  String OAUTH_TOKEN_URL = "/oauth/token";

  /**
   * grant_type
   */
  String REFRESH_TOKEN = "refresh_token";

  /**
   * oauth 客户端信息
   */
  String CLIENT_DETAILS_KEY = PROJECT_PREFIX + OAUTH_PREFIX + "client:details";

  /**
   * {bcrypt} 加密的特征码
   */
  String BCRYPT        = "{bcrypt}";
  /**
   * sys_oauth_client_details 表的字段，不包括client_id、client_secret
   */
  String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
          + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
          + "refresh_token_validity, additional_information, autoapprove";

  /**
   * JdbcClientDetailsService 查询语句
   */
  String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
          + " from sys_oauth_client_details";

  /**
   * 默认的查询语句
   */
  String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

  /**
   * 按条件client_id 查询
   */
  String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

  /***
   * 资源服务器默认bean名称
   */
  String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";
}
