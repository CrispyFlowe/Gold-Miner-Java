




while True:
    write = input("> ")
    try:
        exec(write)
    except BaseException as error:
        print(error)





