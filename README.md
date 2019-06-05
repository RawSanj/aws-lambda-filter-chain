# aws-lambda-filter-chain

AWS Lambda Filter Chain provides a way to add filters similar to `javax.servlet.Filter` to the AWS Lambda Functions handling requests from API Gateway

## Installation

#### Clone the Github repository
```sh
$ git clone https://github.com/RawSanj/aws-lambda-filter-chain.git
```

#### Build library and sample API Gateway Lambda Function
```sh
$ ./build.sh
```

#### Build & Run sample API Gateway Lambda Function locally
Build and run locally
```sh
$ ./build.sh runLocally
```

Skip build and run locally
```sh
$ ./build.sh runLocally --skipBuild
```
**Note:**
Install [AWS SAM CLI](https://github.com/awslabs/aws-sam-cli) and [Docker](https://hub.docker.com/search/?type=edition&offering=community&operating_system=linux%2Cwindows) to run aws lambda functions locally.

#### Test the sample lambda function locally
```sh
$ // Get All User
$ curl  http://127.0.0.1:3000/user
$ // Post User
$ curl -X POST http://localhost:3000/user -H 'Accept: application/json' -H 'Content-Type: application/json' -d '{"userName": "john","fullName": "John Doe","age": 29,"company": "Example"}'
$ // Post Invalid Request i.e. username with special character
$ curl -X POST http://localhost:3000/user -H 'Accept: application/json' -H 'Content-Type: application/json' -d '{"userName": "john@example","fullName": "John Doe","age": 29,"company": "Example"}'
```


## License

Apache License 2.0

Copyright (c) 2019 Sanjay Rawat
