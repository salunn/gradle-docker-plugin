import com.bmuschko.gradle.docker.tasks.image.Dockerfile

// tag::plugins[]
plugins {
    java
    application
    id("com.bmuschko.docker-java-application") version "{project-version}"
}
// end::plugins[]

// tag::extension[]
docker {
    javaApplication {
        baseImage.set("dockerfile/java:openjdk-7-jre")
        maintainer.set("Benjamin Muschko 'benjamin.muschko@gmail.com'")
        ports.set(listOf(9090, 5701))
        tag.set("jettyapp:1.115")
    }
}
// end::extension[]

// tag::exec[]
docker {
    javaApplication {
        exec {
            defaultCommand("server")
            entryPoint("myApp/bin/containerLaunch.sh")
        }
    }
}
// end::exec[]

// tag::dockerfile-addition-instructions[]
tasks.named<Dockerfile>("dockerDistTar") {
    instruction("RUN ls -la")
    environmentVariable("JAVA_OPTS", "-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap")
}
// end::dockerfile-addition-instructions[]

// tag::instruction-template[]
tasks.named<Dockerfile>("dockerDistTar") {
    instructionsFromTemplate(file("Dockerfile.tmpl"))
}
// end::instruction-template[]
// end::instruction-template[]