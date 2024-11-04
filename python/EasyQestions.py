class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def addTwoNumbers(l1, l2):
    """
    :type l1: Optional[ListNode]
    :type l2: Optional[ListNode]
    :rtype: Optional[ListNode]
    """
    carry = 0
    r = ListNode(0)
    res = r
    while l1 is not None or l2 is not None or carry != 0:
        d1 = l1.val if l1 is not None else 0
        d2 = l2.val if l2 is not None else 0
        sum = (d1 + d2 + carry)%10
        newNode = ListNode(sum)
        carry = (d1 + d2 + carry)//10
        r.next = newNode
        r = r.next
        l1 = l1.next if l1 is not None else None
        l2 = l2.next if l2 is not None else None
    
    r.next = None
    
    return res.next



def smallerNumbersThanCurrent(self, nums):
    """
    :type nums: List[int]
    :rtype: List[int]
    """
    sn = sorted(nums)
    res = []
    for i in nums:
        res.append(sn.index(i))
    
    return res