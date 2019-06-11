package test.blz;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils<T> {

    private RedisTemplate<String, T> redisTemplate;

    private HashOperations<String, Object, T> hashOperation;

    private ListOperations<String, T> listOperation;

    private ValueOperations<String, T> valueOperations;

    @Autowired
    RedisUtils (RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperation = redisTemplate.opsForHash();
        this.listOperation = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void putMap (String redisKey, Object key, T data) {
        hashOperation.put(redisKey, key, data);
    }

    public T getMapAsSingleEntry (String redisKey, Object key) {
        return hashOperation.get(redisKey, key);
    }

    public void setExpire (String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    public void removeFromCache(String redisKey, Object key){
        hashOperation.delete(redisKey, key);
    }

    public void clearAll(String key){
        redisTemplate.delete(key);
    }
}