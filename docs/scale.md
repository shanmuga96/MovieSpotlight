# 📈 Scaling Strategy

### From 100 → 10,000 users/day
- Connection pooling
- Caching frequent queries

### From 10,000 → 1,000,000 users/day
- Distributed DB / read replicas
- Queue system for rating updates
- Multiple instances behind a load balancer

### Beyond 10,000,000 users/day
- Microservices architecture
- Distributed caching (Redis / Memcached)
- Optimized queries and indexing
- Rate limiting / throttling
