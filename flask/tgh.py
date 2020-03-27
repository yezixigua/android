import threading



class a(object):

    def __init__(self):
        self.num = 0
        self.mutex = threading.Lock()

    def increase_int_1(self):

        for i in range(1000000):
            self.mutex.acquire()
            self.num += 1
            self.mutex.release()
        print("1: ", self.num)

    def increase_int_2(self):

        for i in range(1000000):
            self.mutex.acquire()
            self.num += 1
            self.mutex.release()

        print("2: ", self.num)

    def start_thread(self):
        t1 = threading.Thread(target=self.increase_int_1)
        t1.start()

        t2 = threading.Thread(target=self.increase_int_2)
        t2.start()
        t1.join()
        t2.join()


ta = a()
ta.start_thread()
print(ta.num)
