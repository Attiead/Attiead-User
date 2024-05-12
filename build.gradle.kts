import com.epages.restdocs.apispec.gradle.OpenApi3Extension


plugins {
  id("java")
  id("org.springframework.boot") version "3.1.2"
  id("io.spring.dependency-management") version "1.1.2"
  id("org.asciidoctor.jvm.convert") version "3.3.2"
  id("com.google.cloud.tools.jib") version "3.2.1"
  id("checkstyle")
  id("com.epages.restdocs-api-spec") version "0.18.2"  // 3. openAPI 플러그인 추가
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_21;
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-web") {
    exclude("org.springframework.boot", "spring-boot-starter-tomcat")
  }
  implementation("org.springframework.boot:spring-boot-starter-undertow")

  implementation("mysql:mysql-connector-java:8.0.32")

  // jwt
  implementation("io.jsonwebtoken:jjwt-api:0.11.5")
  implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
  implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

  // spring kafka
  implementation("org.springframework.kafka:spring-kafka")

  // spring cloud
//	implementation 'org.springframework.cloud:spring-cloud-starter-bus-amqp'
//	implementation 'org.springframework.cloud:spring-cloud-starter-config'
//	implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap'
//	implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
//	implementation 'org.springframework.boot:spring-boot-starter-actuator'

  compileOnly("org.projectlombok:lombok:1.18.30")
  annotationProcessor("org.projectlombok:lombok:1.18.30")

  // test
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.security:spring-security-test")
  implementation("com.github.javafaker:javafaker:1.0.2")

  // 7. RestDocs 추가
  testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
  // 8. openAPI3 추가
  testImplementation("com.epages:restdocs-api-spec-mockmvc:0.18.2")

  //mapstruct
  implementation("org.mapstruct:mapstruct:1.5.3.Final")
  annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

  //validation
  implementation("org.springframework.boot:spring-boot-starter-validation")

  checkstyle("com.puppycrawl.tools:checkstyle:10.9.1")

  // 9. SwaggerUI 추가
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
}

//dependencyManagement {
//	imports {
//		mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
//	}
//}

//
//checkstyle {
//  configFile = file("config/checkstyle/google_checks.xml") // 설정 파일 경로 지정
//  maxWarnings = 0 // 규칙이 어긋나는 코드가 하나라도 있을 경우 빌드 fail
//  toolVersion = "10.9.1" // 처음에 낮은 버전을 사용했더니 깨지는 현상이 있었다. 설정파일의 내용에 맞는 버전을 선택
//}

tasks {
  withType<Test> {
    useJUnitPlatform()
  }

  configure<OpenApi3Extension> {
    setServer("http://localhost:8000")
    title = "RESTDOC + SWAGGER를 이용한 TEST API 문서"
    description = "RestDocsWithSwagger Test Docs"
    version = "0.0.1"
    format = "yaml"
    outputDirectory = "build/apiSpec"
    outputFileNamePrefix = "attiead-user"
  }
}


jib { // https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin
  from {
    //https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#from-closure
    //https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#setting-the-base-image
    image = "eclipse-temurin:17-jre"
  }
  container {
    creationTime = "USE_CURRENT_TIMESTAMP"
  }
}
