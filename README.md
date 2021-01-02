## docker 运行 mongodb
```
docker run 
    --name mongo 
    -p 27017:27017
    -v ~/workspace/docker/mongo:/data/db 
    -e MONGO_INITDB_ROOT_USERNAME=admin 
    -e MONGO_INITDB_ROOT_PASSWORD=admin 
    -d 
    mongo
```

## docker 运行 redis
```
docker run
	--name redis
	-d 
	-p 6379:6379
	redis
```