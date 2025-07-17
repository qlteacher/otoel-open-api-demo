# 获取 AccessToken 接口文档

## 一、接口概述

本接口用于获取登录所需的 AccessToken，通过发送包含用户名、密码等信息的请求，从指定的 OAuth 服务器获取有效的 AccessToken。AccessToken用于后续接口调用的鉴权使用。

## 二、请求信息

### 1. 请求 URL

> https://passport.qlteacher.com/api/oauth2/token

### 2. 请求方法

`POST`

### 3. 请求头

| 字段名           | 类型     | 描述                                      |
|---------------|--------|-----------------------------------------|
| Content-Type  | String | 固定值 `application/x-www-form-urlencoded` |
| Accept        | String | 固定值 `*/*`                               |
| Authorization | String | Basic 认证信息，格式为 `Basic MXMxazoxczFr`     |

### 4. 请求参数

| 字段名        | 类型     | 是否必选 | 描述                  |
|------------|--------|------|---------------------|
| username   | String | 是    | 用户名                 |
| password   | String | 是    | 用户秘钥                |
| scope      | String | 是    | 权限范围，固定值 `account`  |
| grant_type | String | 是    | 授权类型，固定值 `password` |

## 三、响应信息

### 1. 成功响应

当请求成功（HTTP 状态码为 200）时，响应数据为 JSON 格式，包含 `code`、`msg` 和 `data` 字段，其中 `data` 字段包含 `access_token`,`expires_in`,`refresh_token`。

### 响应数据字段说明表

| 字段名                | 类型     | 描述                   |
|--------------------|--------|----------------------|
| code               | int    | 响应状态码，200 表示请求成功     |
| msg                | string | 响应信息，成功时为“”，失败时为错误描述 |
| data               | object | 包含访问令牌等数据的对象         |
| data.access_token  | string | 访问令牌，用于接口调用的身份验证     |
| data.expires_in    | int    | 访问令牌有效期，单位为秒         |
| data.refresh_token | string | 刷新令牌，用于获取新的访问令牌      |

**示例响应：**

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "access_token": "your_access_token",
    "expires_in": 7200,
    "refresh_token": "your_refresh_token"
  }
}
```

### 2. 失败响应

当请求失败时，会抛出 `IOException`，错误信息包含 HTTP 状态码和响应体内容。

## 五、注意事项

- 示例代码中采用配置节存储 AccessToken，建议将 AccessToken 缓存后使用，有效期建议设置为小于expires_in时间(例如一小时)，过期后可以使用 `refresh_token` 刷新或重新请求获取。
- 为加强安全性，从今年开始下发的用户秘钥将是经过加密的，请务必使用加密后的用户秘钥进行登录。
