package com.example.sandbox.model;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

public class DefaultDeployment {

    @SuppressWarnings("unused")
	private static final String WEBAPP_SRC = "src/main/webapp";

    private WebArchive webArchive;
    
 
    MavenDependencyResolver resolver = DependencyResolvers
            .use(MavenDependencyResolver.class)
            .loadMetadataFromPom("pom.xml");
    

    public DefaultDeployment() {
        webArchive = ShrinkWrap.create(WebArchive.class, "test.war").addAsWebInfResource(
        		EmptyAsset.INSTANCE, "beans.xml")
        		.addPackages(true, "com.example.sandbox")
        		.addAsLibraries(resolver.includeDependenciesFromPom("pom.xml").resolveAsFiles())
        		//.addAsResource("log4j-test.xml", "log4j.xml")
                ;
    }

    public DefaultDeployment withPersistence() {
    	

    	     	
        webArchive = webArchive.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
        		.addAsResource("ehcache/ehcache.xml", "ehcache/ehcache.xml")
        		//.addAsWebInfResource("test-ds.xml", "test-ds.xml")
        		;
        return this;
    }

    public DefaultDeployment withImportedData() {
        webArchive = webArchive.addAsResource("import.sql");
        return this;
    }

    public WebArchive getArchive() {
        return webArchive;
    }
}