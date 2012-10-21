'''
Created on 21-Oct-2012

@author: msingh
'''
total =0
def isSolution(nos,k):
    return len(nos) == k+1
 

def permute(nos,k,state,output):
    if(isSolution(nos,k)):
        print output
        global total
        total+=1    
    else:
        k+=1
        #print "state%s,k%s"%(state,k)
        for i in range(len(state)):
            if len(output)==len(nos):
                output[k]=state[i]
            else:
                output.append(state[i])
            pstate=[state[x] for x in range(len(state)) if x != i]            
            permute(nos,k,pstate,output)
            
            
        
if __name__=="__main__":
    list_to_permute=[4,5,6,7,8]    
    permute(list_to_permute,-1,list_to_permute,[])
    print "Total permutations %i"%total