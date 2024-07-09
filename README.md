# watch-dog - Rate limiter application

A rate limiter may be implemented in the client-side or server-side. In the server-side could be implemented in the API server or as a middleware between the client and API.

## Client-side implementation

Client-side implementation has some drawbacks. For example, someone with bad intentions can manipulate the rate limiter rules.

## Server-side implementation

We can use a rate limiter within an API to create specific rules for that API. If you have dozens of micro services, you need to create its own rules for each micro service. But there are two problems with this format.

First, if the rules are repeated, you would be replicating the rules for each micro service.

The second would be if one micro-service calls another, then you would be checking the rule twice, increasing latency.

The third is that depending on the implementation, replicating data can be more expensive, since you have to either create a control database for each micro service, or use a general database to perform control per service.

## Middleware Rate Limiter

The best way to create a rate limiter would be as a middleware between the API client and the server. This way, the rate limiter control would be before our application is hit. 
Typically, many API Gateways and reverse proxies use the rate limiter as a layer of the solution.

Some points of this middleware:

- The application returns HTTP response 429(too many requests) when the limit is exceeded.
- We use distributed Redis to store the counters.
- We store the rules in files where it would be easy to change. We should cache the rules, maybe in a local cache as the rules don't change frequently.

## Algorithms

### Token Bucket

Given a bucket with X number of tokens initially, every time a request comes, we remove a token from the bucket. When the bucket is empty, we return HTTP response 429.

It's necessary to refill the bucket, so normally we have a parameter called refill rate that will calculate the number of token that it will be put in the bucket every second.

### Sliding window log algorithm

### Sliding window counter algorithm

## Race Conditions

https://engineering.classdojo.com/blog/2015/02/06/rolling-rate-limiter/

https://redis.io/docs/latest/operate/rs/databases/active-active/develop/data-types/sorted-sets/

https://redis.io/docs/latest/develop/data-types/sorted-sets/