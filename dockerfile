FROM adoptopenjdk/openjdk8

RUN echo "Start build image"

ARG user=keyboard
RUN useradd --create-home --shell /bin/bash $user
USER $user
WORKDIR /home/$user

COPY --chown=$user:$user ./target/*.jar /home/$user/keyboard.jar

EXPOSE 8080
CMD ["java","-jar","keyboard.jar"]
