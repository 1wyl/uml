@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access_token.expiration}")
    private Long accessTokenExpiration;
    @Value("${jwt.refresh_token.expiration}")
    private Long refreshTokenExpiration;
    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username, accessTokenExpiration);
    } p
    ublic String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username, refreshTokenExpiration);
    } p
    rivate String doGenerateToken(Map<String, Object> claims, String
            username, Long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +
                        expiration * 1000))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    } p
    ublic String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    } p
    ublic Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);