## access application using following swagger 

http://localhost:8080/swagger-ui/index.html



### search book either by author or book name using following input 

[
  {
   
    "author": "Spurlock, Morgan"
   
  },
  {
    
    "name": "Perspective! for Comic Book Artists: How to Achieve a Professional Look in your Artwork"
   
  }
]

### copy ISBN form search and pass to check out method along with quantity

[
  {
    
    "isbn": "0756683424",
    "quantity": 1
  },
  {
   
    "isbn": "0823005674",
    "quantity": 2
  }
]

## repeat the checkout process again 

##look into total books using /api/books and observe total quantity reduced to 0 

"# onlinestore" 
