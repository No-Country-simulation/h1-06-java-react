package io.justina.h106javareact;
// package h1_06_java_react.h1_06_java_react;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer",bearerFormat ="JWT" , type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class H106JavaReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(H106JavaReactApplication.class, args);
	}


    @Bean
	public OpenAPI customOpenAPI() {
        // placeholder for now
        String localLicenceInfoTitle = "H01-06-Java-React";
        String localLicenceInfoVersion = "v0.0.1";
        String localLicenceInfoDescription = """
                <h2>Lorem ipsum dolor sit amet</h2> \n consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et 
                dolore magna aliqua. Laoreet sit amet cursus sit amet dictum sit. Massa enim nec dui nunc mattis enim. 
                Ullamcorper sit amet risus nullam eget felis eget nunc. In metus vulputate eu scelerisque felis imperdiet. 
                Elit ut aliquam purus sit amet luctus. Cursus euismod quis viverra nibh cras pulvinar. Iaculis at erat 
                pellentesque adipiscing. Proin libero nunc consequat interdum. Maecenas ultricies mi eget mauris pharetra 
                et ultrices. Pharetra sit amet aliquam id diam maecenas ultricies mi. Commodo ullamcorper a lacus vestibulum 
                sed arcu non. Morbi tristique senectus et netus et malesuada fames. Amet dictum sit amet justo. \n
                Velit sed ullamcorper morbi tincidunt. Orci sagittis eu volutpat odio facilisis mauris sit amet.
                In arcu cursus euismod quis. Neque ornare aenean euismod elementum nisi quis. Volutpat odio 
                facilisis mauris sit amet massa vitae tortor. Potenti nullam ac tortor vitae purus faucibus ornare 
                suspendisse sed. Posuere morbi leo urna molestie at elementum eu facilisis sed. \n
                Et leo duis ut diam quam nulla porttitor. Donec enim diam vulputate ut pharetra sit amet aliquam id.
                Platea dictumst quisque sagittis purus sit amet. Convallis a cras semper auctor neque vitae. \n
                Volutpat lacus laoreet non curabitur gravida arcu ac tortor. Sapien faucibus et molestie ac feugiat. 
                Placerat duis ultricies lacus sed. Egestas egestas fringilla phasellus faucibus scelerisque. 
                Amet commodo nulla facilisi nullam vehicula ipsum a. Id venenatis a condimentum vitae sapien 
                pellentesque habitant morbi tristique. Curabitur gravida arcu ac tortor dignissim convallis.
                """;

		// Can use method chaining here
		OpenAPI openAPI = new OpenAPI();

		Info licenceInfo = new Info();

        licenceInfo.setTitle(localLicenceInfoTitle);
		licenceInfo.setLicense(new License().name("private").url("https://github.com/Andr7st"));
        licenceInfo.setVersion(localLicenceInfoVersion);
		licenceInfo.setDescription(localLicenceInfoDescription);
		licenceInfo.setTermsOfService("https://github.com/Andr7st");
		/// licenceInfo.setSummary("");

		openAPI.info(licenceInfo);
		return openAPI;
	}

}
