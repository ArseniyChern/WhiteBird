# WhiteBird

##### To run the project you could either run `com.codechallenge.whitebird.WhiteBird.java` class which contains a main method or you could run the unit tests located at `src/test/java/com.codechallenge.whitebird.service.GetTransactionsServiceTest.java`, depending on how you are running it the source will either be taken from `/csv/main/input.csv` or `csv/test/input.csv`

# 

##### The application code itself uses an in-memory data store that I created that essentially just stores a list of the transactions in a static variable that is initialized at runtime by the jvm, the `com.codechallenge.whitebird.service.GetTransactionsServiceImpl.java` uses this data store to perform required application logic. 