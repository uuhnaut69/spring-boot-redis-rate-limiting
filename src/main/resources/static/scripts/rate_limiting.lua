-- rate_limiting.lua
local api_key = KEYS[1]
local window = tonumber(ARGV[1])
local max_requests = tonumber(ARGV[2])
local current_time = redis.call('TIME')
local trim_time = tonumber(current_time[1]) - window
redis.call('ZREMRANGEBYSCORE', api_key, 0, trim_time)
local request_count = redis.call('ZCARD', api_key)

if request_count < max_requests then
    redis.call('ZADD', api_key, current_time[1], current_time[1] .. current_time[2])
    redis.call('EXPIRE', api_key, window)
    return true
end
return false