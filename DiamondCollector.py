"""
Sliding Window
best_rec[i] := largest case size ending at i
when moving the left pointer, it also means we can use best_rec[l]
best_rec[r] = r - l + 1, when l is still valid
so potential answer is best_rec_so_far + best_rec[r]
"""
read = open("__fillerFile__.in", "r").readline
write = open("__fillerFile__.out", "w").write
n, k = map(int, read().split())
diamonds = [int(read()) for _ in range(n)] 
diamonds.sort()
# for i in diamonds:
#     print(i)
ans, l, best_left = 0, 0, 0
best_rec = [0] * n
for r in range(n):
  while diamonds[l] + k < diamonds[r]:
    best_left = max(best_left, best_rec[l])
    l += 1
  best_rec[r] = r - l + 1
  ans = max(ans, best_rec[r] + best_left)
  print(diamonds[r], best_rec[r], "+", best_left, "=", (best_rec[r] + best_left))
print(ans)
# for i in best_rec:
#     print(i)