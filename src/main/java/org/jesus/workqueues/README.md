This lesson is not that much different form the previous one. Here the consumer is called
`Worker` since we are supposed to run more than one in parallel. We can observe how rabbit manages this.

We need to run more than worker at the same time, and also, we need to provide strings as command line arguments to the 
producer NewTask.

Message and queue durability are clearly explained in the tutorial, and we also learnt to set the prefetch count to 1 in order 
not to overload any Worker