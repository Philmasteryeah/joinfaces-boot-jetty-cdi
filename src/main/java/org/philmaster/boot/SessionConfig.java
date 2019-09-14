package org.philmaster.boot;

//@Configuration
//@EnableRedisHttpSession
//public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		return new JedisConnectionFactory();
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
//		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
//		template.setValueSerializer(new JdkSerializationRedisSerializer());
//		template.setConnectionFactory(jedisConnectionFactory());
//		return template;
//	}

//	@Bean
//	ServletRequestListener cayenneServletRequestListener() {
//		System.err.println("ServletRequestListener instantiated");
//		return new ServletRequestListener() {
//
//			@Override
//			public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
//				//System.err.println("request destroyed called ");
//				ObjectContext context = null;
//				try {
//					context = BaseContext.getThreadObjectContext();
//				} catch (IllegalStateException e) {
//					// TODO: handle exception
//					// System.err.println("ignore this: " + e);
//				}
//
////				if (context != null) {
//					disposeContext(context);
////					// System.err.println("should unbind context now");
////					// BaseContext.bindThreadObjectContext(null);
////				}
//			}
//
//			private void disposeContext(ObjectContext octx) {
//
//				if (octx == null || !octx.hasChanges())
//					return;
//				try {
//					octx.commitChanges();
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//
//			}
//		};
//	}

//	@Component
//	@Order(1)
//	public class TransactionFilter extends  {
//
//		@Override
//		public void doFilter(ServletRequest request, javax.servlet.ServletResponse response, FilterChain chain)
//				throws IOException, ServletException {
//			DIBootstrap.
//			CayenneRuntime runtime = WebUtil.getCayenneRuntime(servletContext);
//			RequestHandler handler = runtime.getInjector()
//					.getInstance(RequestHandler.class);
//
//			HttpServletRequest req = (HttpServletRequest) request;
//			//LOG.info("Starting a transaction for req : {}", req.getRequestURI());
//			System.err.println("asd");
//			chain.doFilter(request, response);
//			//LOG.info("Committing a transaction for req : {}", req.getRequestURI());
//
//		}
//
//		// other methods
//	}

//	@WebFilter(filterName = "cayenne-config", displayName = "cayenne-config", urlPatterns = {"/*"})
//	public class TestFilter extends CayenneFilter
//	{
//	    @Override
//	    public void init(FilterConfig config) throws ServletException
//	    {
//	        this.checkAlreadyConfigured(config.getServletContext());
//	        this.servletContext = config.getServletContext();
//	        WebConfiguration configAdapter = new WebConfiguration(config);
//	        Collection modules = configAdapter.createModules(new Module[]{new WebModule()});
//	        ServerRuntime runtime = new ServerRuntime("cayenne-test.xml", (Module[])modules.toArray(new Module[modules.size()]));
//	        WebUtil.setCayenneRuntime(config.getServletContext(), runtime);
//	    }
//	}

//	@Bean
//	public FactoryBean<ObjectContext> cayenneObjectContext(ServerRuntime cayenneRuntime) {
//		return new FactoryBean<ObjectContext>() {
//
//			@Override
//			public ObjectContext getObject() throws Exception {
//				ObjectContext octx = ExtBaseContext.getThreadObjectContextNull();
//				if (octx != null) {
//					return octx;
//				}
//				octx = cayenneRuntime.newContext();
//				ExtBaseContext.bindThreadObjectContext(octx);
//				return octx;
//			}
//
//			@Override
//			public Class<?> getObjectType() {
//				return ObjectContext.class;
//			}
//
//			@Override
//			public boolean isSingleton() {
//				return false;
//			}
//		};
//	}
//
//	private static final String CAYENNE_CONFIG = "cayenne-project.xml";
//
//	@Bean
//	public ServerRuntime cayenneServerRuntime(DataSource dataSource) {
//
//		ServerRuntimeBuilder builder = ServerRuntime.builder()
//				.dataSource(dataSource)
//				.addConfig(CAYENNE_CONFIG);
//
//		return builder.build();
//	}

//}