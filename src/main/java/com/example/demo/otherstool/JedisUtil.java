package com.example.demo.otherstool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import redis.clients.jedis.*;
import redis.clients.util.SafeEncoder;

import java.io.IOException;
import java.util.*;


/**
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @ClassName: JedisUtil
 * @Description: TODO Jedis 工具类
 * @date 2016年3月15日 下午1:18:55
 */
@Slf4j
public final class JedisUtil {

    // 是否是集群
//	private static Boolean isCluster = Boolean.FALSE;
    private static Integer isCluster = 0;

    private static Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    private static Properties systemProperties = null;
    private static JedisCluster jedis;

    static {
        /**
         * 加载环境变量 设置 Jedis 集群节点
         */
        try {
            systemProperties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/redis.properties"));

//			log.error("redis-message:" + systemProperties.getProperty("redis.iscluster"));

            if (StringUtils.isNotBlank(systemProperties.getProperty("redis.iscluster"))) {
                if ("1".equals(systemProperties.getProperty("redis.iscluster"))) {
                    isCluster = 1;
                } else if ("2".equals(systemProperties.getProperty("redis.iscluster"))) {
                    isCluster = 2;
                }
            }

            if (isCluster == 1) {
                // TODO 生产环境 集群
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel1.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel1.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel2.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel2.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel3.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel3.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel4.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel4.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel5.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel5.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel6.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel6.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel7.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel7.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel7.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel7.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel8.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel8.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel8.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel8.port").toString())));
                jedis = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT,
                        Integer.valueOf(systemProperties.getProperty("redis.sentinel.maxattempts")),
                        systemProperties.getProperty("redis.sentinel.password"), new JedisPoolConfig());
            } else if (isCluster == 2) {
                // TODO 聚石塔集群
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel1.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel1.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel2.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel2.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel3.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel3.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel4.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel4.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel5.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel5.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel6.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel6.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel7.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel7.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel7.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel7.jst.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel8.jst.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel8.jst.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel8.jst.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel8.port").toString())));
                jedis = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT,
                        Integer.valueOf(systemProperties.getProperty("redis.sentinel.jst.maxattempts")),
                        systemProperties.getProperty("redis.sentinel.jst.password"), new JedisPoolConfig());

            } else {
                // TODO 测试环境 集群
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.test.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel1.test.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel1.test.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel1.test.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.test.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel2.test.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel2.test.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel2.test.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.test.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel3.test.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel3.test.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel3.test.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.test.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel4.test.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel4.test.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel4.test.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.test.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel5.test.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel5.test.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel5.test.port").toString())));
                if (StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.test.host"))
                        && StringUtils.isNotBlank(systemProperties.getProperty("redis.sentinel6.test.port")))
                    jedisClusterNodes.add(new HostAndPort(systemProperties.getProperty("redis.sentinel6.test.host"),
                            Integer.valueOf(systemProperties.get("redis.sentinel6.test.port").toString())));
                jedis = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT,
                        Integer.valueOf(systemProperties.getProperty("redis.sentinel.test.maxattempts")),
                        systemProperties.getProperty("redis.sentinel.test.password"), new JedisPoolConfig());
            }
        } catch (IOException e) {
            String fullStackTrace = ExceptionUtils.getStackTrace(e);
            log.error(fullStackTrace);
        }
    }

    /**
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        try {
            jedis.set(key.getBytes("UTF-8"), ObjectUtil.toByteArray(value));
        } catch (Exception e) {
//			e.printStackTrace();
        }
    }

    public static String set(String key, String value) {
        try {
            jedis.set(key.getBytes("UTF-8"), value.getBytes("UTF-8"));
            return "";
        } catch (Exception e) {
            String err = StringUtil.getExecptionMessage(e);
            return err;
        }
    }

    /**
     * @param key
     * @param seconds 有效时间（秒）
     * @param value
     */
    public static void setex(String key, int seconds, Object value) {
        try {
            byte[] b = ObjectUtil.toByteArray(value);

            jedis.setex(key.getBytes("UTF-8"), seconds, ObjectUtil.toByteArray(value));
        } catch (Exception e) {
//			e.printStackTrace();
        }
    }

    public static void setexString(String key, int seconds, String value) {
        try {
            byte[] b = ObjectUtil.toByteArray(value);

            jedis.setex(key.getBytes("UTF-8"), seconds, value.getBytes("UTF-8"));
        } catch (Exception e) {
//			e.printStackTrace();
            StringUtil.getExecptionMessage(e);
        }
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        try {
//			log.info("login-info: 开始获取");
            byte[] ret = jedis.get(key.getBytes("UTF-8"));
            if (ret != null && ret.length != 0) {
                return ObjectUtil.toObject(ret);
            }
            return null;
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static String getString(String key) {
        try {
//			log.info("login-info: 开始获取");
            byte[] ret = jedis.get(key.getBytes("UTF-8"));
            if (ret != null && ret.length != 0) {
//				log.info("login-info : [" + key + "]  ===== " + new String(ret,"UTF-8") );
                return new String(ret, "UTF-8");
            }
            return "";
        } catch (Exception e) {
			StringUtil.getExecptionMessage(e);
            return "";
        }
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exist(String key) {
        try {
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
//			e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除
     *
     * @param key
     */
    public static String del(String key) {
        try {
            jedis.del(key.getBytes());
            return "";
        } catch (Exception e) {
			return "错误:"+e.getMessage();
        }
    }

    /**
     * 获取自增列
     *
     * @param key
     * @return
     */
    public static Long incr(String key) {
        try {
            return jedis.incr(key.getBytes());
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static String resetIncr(String key) {
        try {
            return jedis.set(key.getBytes(), "0".getBytes());
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Long incrBy(String key, Long value) {
        try {
            return jedis.incrBy(key.getBytes(), value);
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Object hget(String key, String field) {
        try {
            return new String(jedis.hget(key.getBytes(), field.getBytes()));
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Long hset(String key, String field, String value) {
        try {
            return jedis.hset(key.getBytes(), field.getBytes(), value.getBytes());
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Set<String> hkeys(String key) {
        try {
            Set<String> result = new HashSet<String>();
            Set<byte[]> s = jedis.hkeys(key.getBytes());
            if (s != null && !s.isEmpty()) {
                for (byte[] b : s) {
                    result.add(new String(b));
                }
            }
            return result;
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }

    }

    public static Map<String, String> hgetAll(String key) {
        try {
            Map<String, String> result = new HashMap<String, String>();
            Map<byte[], byte[]> s = jedis.hgetAll(key.getBytes());
            if (s != null && !s.isEmpty()) {
                for (Map.Entry<byte[], byte[]> entry : s.entrySet()) {
                    result.put(new String(entry.getKey()), new String(entry.getValue()));
                }
            }
            return result;
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    /**
     * Set 添加
     *
     * @param key
     * @param
     * @return
     */
    public static Long sadd(String key, String member) {
        try {
            return jedis.sadd(key.getBytes(), SafeEncoder.encodeMany(member));
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Long sadd(String key, String... members) {
        try {
            return jedis.sadd(key.getBytes(), SafeEncoder.encodeMany(members));
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    /**
     * Set 删除
     *
     * @param key
     * @param
     * @return
     */
    public static Long srem(String key, String member) {
        try {
            return jedis.srem(key.getBytes(), SafeEncoder.encodeMany(member));
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Long srem(String key, String... members) {
        try {
            return jedis.srem(key.getBytes(), SafeEncoder.encodeMany(members));
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Set<String> smembers(String key) {
        try {
            Set<String> result = new HashSet<String>();
            Set<byte[]> s = jedis.smembers(key.getBytes());
            if (s != null && !s.isEmpty()) {
                for (byte[] b : s) {
                    result.add(new String(b));
                }
            }
            return result;
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    public static Boolean sismember(String key, String member) {
        try {
            return jedis.sismember(key.getBytes(), member.getBytes());
        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }
    }

    /**
     * 模糊匹配所有keys
     *
     * @param key
     * @return
     */
    public static Set<String> keys(String key) {
        Set<String> result = new HashSet<>();
        try {
            Map<String, JedisPool> clusterNodes = jedis.getClusterNodes();
            for (String k : clusterNodes.keySet()) {
                JedisPool jp = clusterNodes.get(k);
                Jedis connection = jp.getResource();
                try {
                    result.addAll(connection.keys(key));
                } catch (Exception e) {
//					e.printStackTrace();
                } finally {
                    connection.close();// 用完一定要close这个链接！！！
                }
            }
        } catch (Exception e) {
//			e.printStackTrace();
        }
        return result;
    }

    /**
     * 是否允许访问
     *
     * @param key
     * @return
     */
    public static boolean ifAllowed(String key, Integer max_allowed_times) throws Exception {
        boolean flag = true;
        if (exist(key)) {
            // key对应的value自加1
            jedis.incr(key);
            // 取得key对应的value
            String value = jedis.get(key);
            try {
                // 如果在60秒的生存周期内的访问次数大于max_allowed_times，则不允许访问
                if (Integer.parseInt(value) > max_allowed_times) {
                    flag = false;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new Exception(e.getMessage());
            }
        } else {
            // 第一次访问，设置初始值1
            jedis.set(key, "1");
            // 设置生存时间60秒
            jedis.expire(key, 60);
        }

        return flag;
    }
}
