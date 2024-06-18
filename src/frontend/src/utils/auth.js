const AccessTokenKey = 'ACCESS_TOKEN'

// 获取 Token
export function getAccessToken() {
  return localStorage.getItem(AccessTokenKey)
}

// 设置 Token
export function setToken(token) {
  localStorage.setItem(AccessTokenKey, token)
}

// 删除 Token
export function removeToken() {
  localStorage.removeItem(AccessTokenKey)
}
