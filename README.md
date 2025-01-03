This is a project which demonstrates the different Rate Limiter algorithms. This project only deals with the basic idea behind each of these algorithms. It does not delve deep into the specific implementations. Each of these algorithms is implemented in different ways by different systems worldwide.

The algorithms included here:
## [Token Bucket Algorithm](src/main/java/com/pallamsetty/tokenbucket)
Used by Amazon and Stripe.

<img src="assets/Load%20Bucket%20Algorithm.drawio.png" width="600" height="300" />

## [Leaking Bucket Algorithm](src/main/java/com/pallamsetty/leakingbucket)
Used by Shopify

<img src="assets/Leaking%20Bucket%20Algorithm.drawio.png" width="600" height="300" />

## [Fixed Window Counter Algorithm](src/main/java/com/pallamsetty/fixedwindowcounter)

<img src="assets/Fixed%20Window%20Counter%20Algorithm.png" width="600" height="300" />

## [Sliding Window Log Algorithm](src/main/java/com/pallamsetty/slidingwindowlog)

<img src="assets/Sliding%20Window%20Log.drawio.png" width="600" height="300" />

**This project also includes unit tests to test these algorithms. I have added at least one test for each of these algorithms:**
- [Token Bucket Algorithm Test(s)](src/test/java/com/pallamsetty/tokenbucket)
- [Leaking Bucket Algorithm Test(s)](src/test/java/com/pallamsetty/leakingbucket)
- [Fixed Window Counter Algorithm Test(s)](src/test/java/com/pallamsetty/fixedwindowcounter)
- [Sliding Window Log Algorithm Test(s)](src/test/java/com/pallamsetty/slidingwindowlog)
