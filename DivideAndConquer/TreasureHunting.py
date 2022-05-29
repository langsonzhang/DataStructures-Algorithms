# A treasure hunter has a map of n treasures. 
# For each treasure i, the treasure hunter assigns two integer valued parameters,
# namely the easiness xi to obtain the treasure, and its value yi. 
# The treasure hunter is organizing their trip and wants to identify treasures that are valuable to them. 
# Specifically, a treasure i is valuable if there is no other treasure j with xj ≥ xi and yj ≥ yi. 
# The treasure hunter wants to count the number of valuable treasures on their map.

# You may assume that two distinct treasures differ in at least one of the two parameters i.e., for distinct i and j, either xi ̸= xj or yi ̸= yj.



class Treasure:
    x: int
    y: int
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return "({}, {})".format(self.x, self.y)


def getValuable(treasures: [Treasure]) -> [Treasure]:
    if len(treasures) <= 1:
        return treasures[0:]
    left = getValuable(treasures[0:len(treasures)//2])
    right = getValuable(treasures[len(treasures)//2:])
    ret = merge(left, right)
    return ret

def merge(left, right):
    ret = []

    # pointers for left and right
    l = 0; r = 0
    while (l < len(left) and r < len(right)):
        # get the treasures to consider
        lt = left[l]
        rt = right[r]

        if (lt.x < rt.x):
            if lt.y > rt.y:
                # lt valuable so add it
                ret.append(lt)
            l += 1
        elif lt.x == rt.x:
            if lt.y > rt.y:
                # lt valuable, rt not valuable
                ret.append(lt)
            else:
                # rt valuable, lt not valuable
                ret.append(rt)
            l += 1; r += 1
        else:
            if rt.y > lt.y:
                # rt valuable
                ret.append(rt)
            r += 1

    # any remaining ones must be valuable, as their x values must be strictly greater than everything else
    if l < len(left):
        ret += left[l:]
    if r < len(right):
        ret += right[r:]
    return ret




if __name__ == '__main__':
    test = [
        Treasure(5,10),
        Treasure(6,4),
        Treasure(2,3),
        Treasure(54,1),
        Treasure(-5,0),
        Treasure(23, 23),
        Treasure(23,14),
        Treasure(5,14),
        Treasure(5,23),
    ]
    result = getValuable(test)
    print("results")
    print(' '.join(str(t) for t in result))
    exit(1)

