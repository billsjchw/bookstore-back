conn = new Mongo("localhost:27017");
db = conn.getDB("bookstore");

db.avatars.drop();
db.covers.drop();
db.introductions.drop();

db.createCollection("avatars");
db.createCollection("covers");
db.createCollection("introductions");
